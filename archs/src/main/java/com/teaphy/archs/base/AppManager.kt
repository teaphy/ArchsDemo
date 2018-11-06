package com.teaphy.archs.base

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Intent
import java.util.LinkedList

/**
 * 用于管理所有activity,和在前台的 activity
 * 可以通过直接持有AppManager对象执行对应方法
 * 也可以通过eventbus post事件,远程遥控执行对应方法
 */
class AppManager(private var mApplication: Application?) {
	protected val TAG = this.javaClass.simpleName

	//管理所有activity
	var mActivityList: MutableList<Activity> = mutableListOf()

	/**
	 * 将在前台的activity保存
	 */
	var currentActivity: Activity? = null


	/**
	 * 让在前台的activity,打开下一个activity
	 */
	fun startActivity(intent: Intent) {
		if (currentActivity == null) {
			//如果没有前台的activity就使用new_task模式启动activity
			intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
			mApplication!!.startActivity(intent)
			return
		}
		currentActivity!!.startActivity(intent)
	}

	/**
	 * 让在前台的activity,打开下一个activity
	 */
	fun startActivity(activityClass: Class<*>) {
		startActivity(Intent(mApplication, activityClass))
	}

	/**
	 * 释放资源
	 */
	fun release() {
		mActivityList.clear()
		currentActivity = null
		mApplication = null
	}


	/**
	 * 添加Activity到集合
	 */
	fun addActivity(activity: Activity) {

		synchronized(AppManager::class.java) {
			if (!mActivityList.contains(activity)) {
				mActivityList.add(activity)
			}
		}
	}

	/**
	 * 删除集合里的指定activity
	 *
	 * @param activity
	 */
	fun removeActivity(activity: Activity) {
		synchronized(AppManager::class.java) {
			if (mActivityList.contains(activity)) {
				mActivityList.remove(activity)
			}
		}
	}

	/**
	 * 删除集合里的指定位置的activity
	 *
	 * @param location
	 */
	fun removeActivity(location: Int): Activity? {
		synchronized(AppManager::class.java) {
			if (location > 0 && location < mActivityList.size) {
				return mActivityList.removeAt(location)
			}
		}
		return null
	}

	/**
	 * 关闭指定activity
	 *
	 * @param activityClass
	 */
	fun killActivity(activityClass: Class<*>) {
		for (activity in mActivityList) {
			if (activity.javaClass == activityClass) {
				activity.finish()
			}
		}
	}


	/**
	 * 指定的activity实例是否存活
	 *
	 * @param activity
	 * @return
	 */
	fun activityInstanceIsLive(activity: Activity): Boolean {
		return mActivityList.contains(activity)
	}


	/**
	 * 指定的activity class是否存活(一个activity可能有多个实例)
	 *
	 * @param activityClass
	 * @return
	 */
	fun activityClassIsLive(activityClass: Class<*>): Boolean {
		for (activity in mActivityList) {
			if (activity.javaClass == activityClass) {
				return true
			}
		}

		return false
	}


	/**
	 * 关闭所有activity
	 */
	fun killAll() {

		val iterator = mActivityList.iterator()
		while (iterator.hasNext()) {
			iterator.next().finish()
			iterator.remove()
		}

	}


	/**
	 * 退出应用程序
	 */
	fun AppExit() {
		try {
			killAll()
			val activityMgr = mApplication!!.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
			activityMgr.killBackgroundProcesses(mApplication!!.packageName)
			System.exit(0)
		} catch (e: Exception) {
			e.printStackTrace()
		}

	}
}
