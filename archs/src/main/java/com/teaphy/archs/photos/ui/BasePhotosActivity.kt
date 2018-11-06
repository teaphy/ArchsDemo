package com.teaphy.archs.photos.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.teaphy.archs.utils.ImmeriveUtils
import com.teaphy.archs.photos.observe.CancelSubject
import com.teaphy.archs.photos.observe.ICancelObserve


/**
 * @desc
 * @author teaphy
 * @time 2018/9/30 下午2:02
 */
abstract class BasePhotosActivity : AppCompatActivity(), ICancelObserve {

	protected var openWhiteStatusBar: Boolean = false

	override fun onCreate(savedInstanceState: Bundle?) {
		immersive()
		super.onCreate(savedInstanceState)
		CancelSubject.obtain().subcribeObserve(this)
	}

	override fun onDestroy() {
		super.onDestroy()
		CancelSubject.obtain().unsubcribeObserve(this)
	}

	override fun doCancel() {
		finish()
	}


	/**
	 * 具体沉浸的样式，可以根据需要自行修改状态栏和导航栏的颜色
	 */
	fun immersive() {
		ImmeriveUtils.immersive(this, android.R.color.transparent, android.R.color.transparent)
	}
}