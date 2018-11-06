package com.teaphy.archs.demo.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import com.teaphy.archs.adapter.CommonPagerAdapter
import com.teaphy.archs.base.BaseActivity
import com.teaphy.archs.demo.R
import com.teaphy.archs.demo.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {

	val listFragment = mutableListOf<Fragment>()
	val listTitle = mutableListOf<String>()

	override fun getLayoutId(): Int {
		return R.layout.activity_main
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun initData() {
		super.initData()

		with(listFragment) {
			add(HomeFragment())
			add(NewsFragment())
			add(PictureFragment())
			add(PreferenceFragment())
			add(FunctionsFragment())
			add(PermissionFragment())
		}

		with(listTitle) {
			add("首页")
			add("新闻")
			add("图片")
			add("功能条")
			add("功能键")
			add("权限")
		}
	}

	override fun initView() {
		super.initView()

		val mAdapter = CommonPagerAdapter(supportFragmentManager, listFragment, listTitle)
		mBinding.vpMain.adapter = mAdapter
		mBinding.tlFeatures.setupWithViewPager(mBinding.vpMain)
	}
}
