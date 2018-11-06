package com.teaphy.archs.demo.ui.main


import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import com.teaphy.archs.permissions.IGrantedFailure
import com.teaphy.archs.permissions.IGrantedSuccess
import com.teaphy.archs.permissions.RxPermissionUtil

import com.teaphy.archs.demo.R
import kotlinx.android.synthetic.main.fragment_permission.*

class PermissionFragment : Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_permission, container, false)
	}

	override fun onResume() {
		super.onResume()
		setListener()
	}

	private fun setListener() {
		btnCamera.setOnClickListener {
			RxPermissionUtil.getInstance(this@PermissionFragment)
					.requestCamera(object : IGrantedSuccess {
						override fun onGrantedSuccess() {
							showMessage("已授权")
						}
					}, object : IGrantedFailure {
						override fun onGrantedFailure() {
							showMessage("未授权")
						}
					})
		}

		btnStorage.setOnClickListener {
			RxPermissionUtil.getInstance(this@PermissionFragment)
					.requestExternalStorage(object : IGrantedSuccess {
						override fun onGrantedSuccess() {
							showMessage("已授权")
						}
					}, object : IGrantedFailure {
						override fun onGrantedFailure() {
							showMessage("未授权")
						}
					})
		}

		btnSms.setOnClickListener {
			RxPermissionUtil.getInstance(this@PermissionFragment)
					.requestSendSms(object : IGrantedSuccess {
						override fun onGrantedSuccess() {
							showMessage("已授权")
						}
					}, object : IGrantedFailure {
						override fun onGrantedFailure() {
							showMessage("未授权")
						}
					})
		}

		btnPhone.setOnClickListener {
			RxPermissionUtil.getInstance(this@PermissionFragment)
					.requestCallPhone(object : IGrantedSuccess {
						override fun onGrantedSuccess() {
							showMessage("已授权")
						}
					}, object : IGrantedFailure {
						override fun onGrantedFailure() {
							showMessage("未授权")
						}
					})
		}

		btnPhoneState.setOnClickListener {
			RxPermissionUtil.getInstance(this@PermissionFragment)
					.requestPhoneState(object : IGrantedSuccess {
						override fun onGrantedSuccess() {
							showMessage("已授权")
						}
					}, object : IGrantedFailure {
						override fun onGrantedFailure() {
							showMessage("未授权")
						}
					})
		}

		btnLocation.setOnClickListener {
			RxPermissionUtil.getInstance(this@PermissionFragment)
					.requestLocation(object : IGrantedSuccess {
						override fun onGrantedSuccess() {
							showMessage("已授权")
						}
					}, object : IGrantedFailure {
						override fun onGrantedFailure() {
							showMessage("未授权")
						}
					})
		}

		btnSpecify.setOnClickListener {


			RxPermissionUtil.getInstance(this@PermissionFragment)
					.requestSpecify(Manifest.permission.CAMERA,
							object : IGrantedSuccess {
								override fun onGrantedSuccess() {
									showMessage("已授权")
								}
							}, object : IGrantedFailure {
						override fun onGrantedFailure() {
							showMessage("未授权")
						}
					})
		}

		btnEach.setOnClickListener {

			val permissions = arrayOf(Manifest.permission.CAMERA,
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE)

			RxPermissionUtil.getInstance(this@PermissionFragment)
					.requestEachPermissions(object : IGrantedSuccess {
						override fun onGrantedSuccess() {
							showMessage("已授权")
						}
					}, object : IGrantedFailure {
						override fun onGrantedFailure() {
							showMessage("未授权")
						}
					}, *permissions)
		}

		btnAll.setOnClickListener {

			val permissions = arrayOf(Manifest.permission.CAMERA,
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE)

			RxPermissionUtil.getInstance(this@PermissionFragment)
					.requestAllPermissions(object : IGrantedSuccess {
						override fun onGrantedSuccess() {
							showMessage("已授权")
						}
					}, object : IGrantedFailure {
						override fun onGrantedFailure() {
							showMessage("未授权")
						}
					}, *permissions)
		}


	}

	fun showMessage(message: String) {
		ToastUtils.showShort(message)
	}
}

