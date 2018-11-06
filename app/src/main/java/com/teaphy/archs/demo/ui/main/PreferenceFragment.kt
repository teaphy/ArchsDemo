package com.teaphy.archs.demo.ui.main


import android.content.Intent
import android.support.v4.app.Fragment
import com.teaphy.archs.base.BaseFragment

import com.teaphy.archs.demo.R
import com.teaphy.archs.demo.databinding.FragmentPreferenceBinding
import com.teaphy.archs.demo.ui.location.LocationActivity
import com.teaphy.archs.demo.ui.news.NewsActivity
import com.teaphy.archs.demo.ui.photos.PhotosTestActivity
import com.teaphy.archs.demo.ui.zxing.CaptureTestActivity
import com.teaphy.archs.demo.ui.zxing.QrCodeActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class PreferenceFragment :BaseFragment<FragmentPreferenceBinding>() {
	override fun getLayoutId(): Int {
		return R.layout.fragment_preference
	}

	override fun initView() {
		super.initView()
		mBinding.pifvIncome.tvDesc.text = "360元"
	}

	override fun setListener() {
		super.setListener()

		mBinding.pivNews.setOnClickListener {
			val intent = Intent(context!!, NewsActivity::class.java)
			startActivity(intent)
		}

		mBinding.pivLocation.setOnClickListener {
			val intent = Intent(context!!, LocationActivity::class.java)
			startActivity(intent)
		}

		mBinding.cameraPreView.setOnClickListener {
			val intent = Intent(context!!, PhotosTestActivity::class.java)
			startActivity(intent)
		}

		mBinding.scanPreView.setOnClickListener {
			val intent = Intent(context!!,QrCodeActivity::class.java)
			startActivity(intent)
		}
	}
}
