package com.teaphy.archs.base

import android.content.Context
import android.os.Build
import android.support.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils
import com.teaphy.archs.picture.IPictureStrategy
import com.teaphy.archs.picture.PictureHelper
import com.teaphy.archs.utils.NotificationHelper

abstract class BaseApplication : MultiDexApplication() {

	companion object {
		private lateinit var application: BaseApplication

		fun getContext(): Context? {
			return application.baseContext
		}

		fun getInstance(): BaseApplication {
			return application
		}


	}

	lateinit var mAppManager: AppManager

	// 对Activity的生命周期统一管理
	private lateinit var mActivityLifecycle: ActivityLifecycle

	override fun onCreate() {
		super.onCreate()

		initGeneralConfig()

		application = this

		// 在主线程中 初始化
		initMain()

		// 在子线程中初始化
		initBg()

		// 注册监听
		registerActivityLifecycleCallbacks(mActivityLifecycle)
	}


	override fun onTerminate() {
		super.onTerminate()
		unregisterActivityLifecycleCallbacks(mActivityLifecycle)

		// AppManager释放资源
		mAppManager.release()
	}

	/**
	 *  在主线程中初始化
	 * @autor teaphy
	 * @date 2018/5/14 上午11:22
	 */
	open fun initMain() {
		// 工具类初始化
		Utils.init(this)

		mAppManager = AppManager(this)
		mActivityLifecycle = ActivityLifecycle(mAppManager)

		// 初始化消息通知默认频道
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationHelper.initDefaultChannel(application)
		}
	}

	/**
	 * 在子线程中初始化
	 * @autor teaphy
	 * @date 2018/5/14 上午11:20
	 */
	open fun initBg() {

	}

	/**
	 * 初始化配置
	 */
	abstract fun initGeneralConfig()
}