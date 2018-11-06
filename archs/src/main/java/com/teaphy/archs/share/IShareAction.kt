package com.teaphy.archs.share

import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage

/**
 * @desc 分享策略
 * @author teaphy
 * @time 2018/8/30 下午3:02
 */
interface IShareAction {

	/**
	 * 纯文本
	 */
	fun shareText(type: SHARE_MEDIA, text: String, image: UMImage)

	/**
	 * 分享图片
	 */
	fun shareImage(type: SHARE_MEDIA, text: String?, image: UMImage)

	/**
	 * 分享多图
	 */
	fun shareImages(type: SHARE_MEDIA, text: String?, vararg images: UMImage)

	/**
	 * 分享链接
	 */
	fun shareUrl(type: SHARE_MEDIA, url: String, title: String,
	             desc: String, image: UMImage)

	/**
	 * 分享视频
	 */
	fun shareVideo(type: SHARE_MEDIA, url: String, title: String,
	               desc: String, image: UMImage)

	/**
	 * 分享音乐
	 */
	fun shareMusic(type: SHARE_MEDIA, url: String, title: String,
	               desc: String, image: UMImage)
}