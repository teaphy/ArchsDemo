package com.teaphy.archs.demo.ui.location

import android.databinding.adapters.ViewGroupBindingAdapter.setListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.amap.api.location.AMapLocation
import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.teaphy.archs.location.ILocationCallback
import com.teaphy.archs.location.LocationObserve
import com.teaphy.archs.location.LocationResult
import com.teaphy.archs.demo.R
import kotlinx.android.synthetic.main.activity_location.*
import timber.log.Timber

class LocationActivity : AppCompatActivity() {

	private lateinit var locationObserve: LocationObserve

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_location)

		initView()

		setListener()
	}

	private fun initView() {
		locationObserve = LocationObserve(this, lifecycle, false,0, object : ILocationCallback {
			override fun onChange(locationResult: LocationResult?) {

				if (null != locationResult) {
					Timber.e("location: $locationResult")

					tvLocation.text = locationResult.toString()
				} else {
					ToastUtils.showShort("定位失败")
				}
			}
		})

		lifecycle.addObserver(locationObserve)
	}

	private fun setListener() {

	}


}
