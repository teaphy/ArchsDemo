package com.teaphy.archs.share

import android.app.Activity
import android.graphics.Bitmap
import android.support.v4.app.Fragment
import com.umeng.socialize.media.UMImage
import java.io.File

/**
 * @desc 创建友盟图片资源
 * @author teaphy
 * @time 2018/8/30 下午3:40
 */
class UMImageSource : IShareImageSource {

	private var mActivity: Activity? = null
	private var mFragment: Fragment? = null

	constructor(activity: Activity) {
		mActivity = activity
	}

	constructor(fragment: Fragment) {
		mFragment = fragment
	}

	/**
	 * 获得当前Activity
	 */
	private fun obtainActivity(): Activity {
		if (mFragment != null) {
			mActivity = mFragment!!.activity
		}

		return mActivity ?: throw Throwable("the activity is not initialize")
	}

	// 网络图片
	override fun produceImage(url: String): UMImage {
		return UMImage(obtainActivity(), url)
	}

	// 本地图片
	override fun produceImage(file: File): UMImage {
		return UMImage(obtainActivity(), file)
	}

	// 资源文件
	override fun produceImage(imageRes: Int): UMImage {
		return UMImage(obtainActivity(), imageRes)
	}

	// Bitmap文件
	override fun produceImage(bitmap: Bitmap): UMImage {
		return UMImage(obtainActivity(), bitmap)
	}

	// 字节流
	override fun produceImage(bytes: ByteArray): UMImage {
		return UMImage(obtainActivity(), bytes)
	}
}