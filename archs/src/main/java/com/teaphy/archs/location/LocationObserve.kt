package com.teaphy.archs.location

import android.Manifest
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.app.FragmentActivity
import com.blankj.utilcode.util.ToastUtils
import com.teaphy.archs.permissions.IGrantedFailure
import com.teaphy.archs.permissions.IGrantedSuccess
import com.teaphy.archs.permissions.RxPermissionUtil

/**
 * @desc 定位 LifecycleObserver
 * @author teaphy
 * @time 2018/8/30 下午4:39
 */
class LocationObserve(val activity: FragmentActivity, private val lifecycle: Lifecycle, isSingle: Boolean,
                      intervalLocation: Long, callback: ILocationCallback) : LifecycleObserver {

	val locationStrategy = LocationHelper()

	init {
		locationStrategy.initLocation(activity, isSingle, intervalLocation, callback)
	}


	@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
	fun onCreate() {

	}

	@OnLifecycleEvent(Lifecycle.Event.ON_START)
	fun onStart() {
		if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
			// 开启定位
			locationStrategy.startLocation()
		}
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
	fun onResume() {

	}

	@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	fun onPause() {
		// 停止定位
		locationStrategy.stopLocation()
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_STOP)
	fun onStop() {
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	fun onDestroy() {
		/**
		 * 清除定位
		 */
		locationStrategy.destroyClient()
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_ANY)
	fun onAny() {
	}
}