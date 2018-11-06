package com.teaphy.archs.share

import android.app.Activity
import android.support.v4.app.Fragment
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMVideo
import com.umeng.socialize.media.UMWeb
import com.umeng.socialize.media.UMusic

/**
 * @desc
 * @author tiany
 * @time 2018/10/25 下午4:06
 */
class UMShareAction private constructor(private val umShareListener: UMShareListener) : IShareAction{
	private var mActivity: Activity? = null
	private var mFragment: Fragment? = null

	constructor(activity: Activity, umShareListener: UMShareListener): this(umShareListener) {
		mActivity = activity
	}

	constructor(fragment: Fragment, umShareListener: UMShareListener): this(umShareListener) {
		mFragment = fragment
	}

	/**
	 * 获得当前Activity
	 */
	private fun obtainActivity() : Activity{
		if (mFragment != null) {
			mActivity = mFragment!!.activity
		}

		return mActivity ?: throw Throwable("the activity is not initialize")
	}

	/**
	 * 分享文本
	 */
	override fun shareText(type: SHARE_MEDIA, text: String, image: UMImage) {
		with(ShareAction(obtainActivity())) {
			withText(text)
			withMedia(image)
			platform = type
			setCallback(umShareListener)
			share()
		}
	}

	/**
	 * 分享单图
	 */
	override fun shareImage(type: SHARE_MEDIA, text: String?, image: UMImage) {
		with(ShareAction(obtainActivity())) {
			if (text != null) {
				withText(text)
			}
			withMedia(image)
			platform = type
			setCallback(umShareListener)
			share()
		}
	}

	/**
	 * 分享多图
	 * @param type 分享平台
	 * @param url: 链接
	 * @param title: 标题
	 * @param desc: 描述
	 * @param image: 视频的图片
	 */
	override fun shareImages(type: SHARE_MEDIA, text: String?, vararg images: UMImage) {
		with(ShareAction(obtainActivity())) {
			if (text != null) {
				withText(text)
			}
			withMedias(*images)
			platform = type
			setCallback(umShareListener)
			share()
		}
	}


	/**
	 * 分享链接
	 * @param type 分享平台
	 * @param url: 链接
	 * @param title: 标题
	 * @param desc: 描述
	 * @param image: 视频的图片
	 */
	override fun shareUrl(type: SHARE_MEDIA, url: String, title: String, desc: String, image: UMImage) {
		val umWeb = UMWeb(url, title, desc, image)
		with(ShareAction(obtainActivity())) {
			withMedia(umWeb)
			platform = type
			setCallback(umShareListener)
			share()
		}
	}

	/**
	 * 分享视频
	 * @param type 分享平台
	 * @param url: 视频的播放链接
	 * @param title: 视频的标题
	 * @param desc: 视频的描述
	 * @param image: 视频的图片
	 */
	override fun shareVideo(type: SHARE_MEDIA, url: String, title: String, desc: String, image: UMImage) {
		val umVideo = UMVideo(url)
		with(umVideo) {
			this.title = title
			setThumb(image)
			description = desc
		}

		with(ShareAction(obtainActivity())) {
			withMedia(umVideo)
			platform = type
			setCallback(umShareListener)
			share()
		}
	}

	/**
	 * 分享音乐
	 * @param type 分享平台
	 * @param url: 音乐的播放链接
	 * @param title: 音乐的标题
	 * @param desc: 音乐的描述
	 * @param image: 音乐的图片
	 */
	override fun shareMusic(type: SHARE_MEDIA, url: String, title: String, desc: String, image: UMImage) {
		val umVideo = UMusic(url)
		with(umVideo) {
			this.title = title
			setThumb(image)
			description = desc
		}

		with(ShareAction(obtainActivity())) {
			withMedia(umVideo)
			platform = type
			setCallback(umShareListener)
			share()
		}
	}
}