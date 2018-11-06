package com.teaphy.archs.base


import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * 用于统一管理 Activity的生命周期
 * @autor teaphy
 * @date 2018/5/14 上午11:37
 */
class ActivityLifecycle(private val mAppManager: AppManager) : Application.ActivityLifecycleCallbacks {

	override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
		if (null != activity) {
			mAppManager.addActivity(activity)
		}
	}

	override fun onActivityStarted(activity: Activity) {

	}

	override fun onActivityResumed(activity: Activity) {
		mAppManager.currentActivity = activity
	}

	override fun onActivityPaused(activity: Activity) {
		if (mAppManager.currentActivity != null
				&& mAppManager.currentActivity == activity) {
			mAppManager.currentActivity = null
		}
	}

	override fun onActivityStopped(activity: Activity) {

	}

	override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

	}

	override fun onActivityDestroyed(activity: Activity) {
		mAppManager.removeActivity(activity)
	}
}
