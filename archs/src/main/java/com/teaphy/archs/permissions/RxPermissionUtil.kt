package com.teaphy.archs.permissions

import android.Manifest
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * @author teaphy
 * @desc RxPermission工具类
 * @date 2017/3/17
 */
class RxPermissionUtil {

	private lateinit var mRxPermission: RxPermissions

	private constructor(fragmentActivity: FragmentActivity) {
		mRxPermission = RxPermissions(fragmentActivity)
	}

	private constructor(fragment: Fragment) {
		mRxPermission = RxPermissions(fragment)
	}


	companion object {
		fun getInstance(fragmentActivity: FragmentActivity): RxPermissionUtil {
			return RxPermissionUtil(fragmentActivity)
		}

		fun getInstance(fragment: Fragment): RxPermissionUtil {
			return RxPermissionUtil(fragment)
		}
	}

	/**
	 * 验证指定权限授权
	 * @author teaphy
	 * @date 2018/7/28 下午4:38
	 */
	fun requestSpecify(
			permission: String,
			grantedSuccess: IGrantedSuccess,
			grantedFailure: IGrantedFailure) {

		val isPermissionsGranted = mRxPermission
				.isGranted(permission)

		if (isPermissionsGranted) {//已经申请过，直接执行操作
			grantedSuccess.onGrantedSuccess()
		} else {
			mRxPermission
					.request(permission)
					.subscribe { granted ->
						if (granted!!) {
							grantedSuccess.onGrantedSuccess()
						} else {
							grantedFailure.onGrantedFailure()
						}
					}
		}
	}

	/**
	 * 申请单个或者多个权限,不在乎是否不再询问和哪个权限申请失败，只要有一个失败就执行失败操作：
	 * @author teaphy
	 * @date 2018/7/28 下午4:47
	 */
	fun requestAllPermissions(
			grantedSuccess: IGrantedSuccess,
			grantedFailure: IGrantedFailure,
			vararg permissions: String) {
		mRxPermission.request(*permissions)
				.subscribe { granted ->
					if (granted!!) {
						grantedSuccess.onGrantedSuccess()
					} else {
						grantedFailure.onGrantedFailure()
					}
				}

	}

	/**
	 * 申请多个权限，在乎是否不再询问和哪个权限申请失败：
	 * @author teaphy
	 * @date 2018/7/28 下午4:47
	 */
	fun requestEachPermissions(grantedSuccess: IGrantedSuccess,
	                           grantedFailure: IGrantedFailure,
	                           vararg permissions: String) {
		mRxPermission.requestEach(*permissions)
				.subscribe {
					when {
						it.granted -> // 已授权
							grantedSuccess.onGrantedSuccess()
						it.shouldShowRequestPermissionRationale -> //拒绝权限请求
							grantedFailure.onGrantedFailure()
						else -> // 拒绝权限请求,并不再询问
							// 可以提醒用户进入设置界面去设置权限
							grantedFailure.onGrantedFailure()
					}
				}

	}

	/**
	 * 请求摄像头权限
	 */
	fun requestCamera(grantedSuccess: IGrantedSuccess,
	                  grantedFailure: IGrantedFailure) {
		//先确保是否已经申请过摄像头，和写入外部存储的权限
		requestAllPermissions(grantedSuccess, grantedFailure,
				Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
	}


	/**
	 * 请求外部存储的权限
	 */
	fun requestExternalStorage(grantedSuccess: IGrantedSuccess,
	                           grantedFailure: IGrantedFailure) {
		//先确保是否已经申请过摄像头，和写入外部存储的权限
		val isPermissionsGranted = mRxPermission
				.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)

		if (isPermissionsGranted) {//已经申请过，直接执行操作
			grantedSuccess.onGrantedSuccess()
		} else {//没有申请过，则申请
			mRxPermission
					.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
					.subscribe { granted ->
						if (granted!!) {
							grantedSuccess.onGrantedSuccess()
						} else {
							grantedFailure.onGrantedFailure()
						}
					}
		}
	}


	/**
	 * 请求发送短信权限
	 */
	fun requestSendSms(grantedSuccess: IGrantedSuccess,
	                   grantedFailure: IGrantedFailure) {
		//先确保是否已经申请过权限
		val isPermissionsGranted = mRxPermission
				.isGranted(Manifest.permission.SEND_SMS)

		if (isPermissionsGranted) {//已经申请过，直接执行操作
			grantedSuccess.onGrantedSuccess()
		} else {//没有申请过，则申请
			mRxPermission
					.request(Manifest.permission.SEND_SMS)
					.subscribe { granted ->
						if (granted!!) {
							grantedSuccess.onGrantedSuccess()
						} else {
							grantedFailure.onGrantedFailure()
						}
					}
		}
	}


	/**
	 * 请求打电话权限
	 */
	fun requestCallPhone(grantedSuccess: IGrantedSuccess,
	                     grantedFailure: IGrantedFailure) {
		//先确保是否已经申请过权限
		val isPermissionsGranted = mRxPermission
				.isGranted(Manifest.permission.CALL_PHONE)

		if (isPermissionsGranted) {//已经申请过，直接执行操作
			grantedSuccess.onGrantedSuccess()
		} else {//没有申请过，则申请
			mRxPermission
					.request(Manifest.permission.CALL_PHONE)
					.subscribe { granted ->
						if (granted!!) {
							grantedSuccess.onGrantedSuccess()
						} else {
							grantedFailure.onGrantedFailure()
						}
					}
		}
	}


	/**
	 * 请求获取手机状态的权限
	 */
	fun requestPhoneState(grantedSuccess: IGrantedSuccess,
	                      grantedFailure: IGrantedFailure) {
		//先确保是否已经申请过权限
		val isPermissionsGranted = mRxPermission
				.isGranted(Manifest.permission.READ_PHONE_STATE)

		if (isPermissionsGranted) {//已经申请过，直接执行操作
			grantedSuccess.onGrantedSuccess()
		} else {//没有申请过，则申请
			mRxPermission
					.request(Manifest.permission.READ_PHONE_STATE)
					.subscribe { granted ->
						if (granted!!) {
							grantedSuccess.onGrantedSuccess()
						} else {
							grantedFailure.onGrantedFailure()
						}
					}
		}
	}


	/**
	 * 请求定位权限
	 */
	fun requestLocation(grantedSuccess: IGrantedSuccess,
	                    grantedFailure: IGrantedFailure) {
		
		val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
				Manifest.permission.ACCESS_FINE_LOCATION,
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.READ_PHONE_STATE)
		
		requestAllPermissions(grantedSuccess, grantedFailure, *permissions)
		
	}
}
