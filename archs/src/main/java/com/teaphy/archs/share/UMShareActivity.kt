package com.teaphy.archs.share

import android.graphics.Bitmap
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import java.io.File

/**
 * @desc 友盟分享 Activity基类
 * @author tiany
 * @time 2018/10/25 下午3:46
 */
abstract class UMShareActivity : AppCompatActivity(), IShareAction, IShareImageSource, UMShareListener{


	private lateinit var umShareAction: UMShareAction
	private lateinit var umImageSource: UMImageSource

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		umShareAction = UMShareAction(this, this)
		umImageSource = UMImageSource(this)

	}

	/**
	 * 分享文本
	 */
	override fun shareText(type: SHARE_MEDIA, text: String, image: UMImage) {
		umShareAction.shareText(type, text, image)
	}

	/**
	 * 分享单图
	 */
	override fun shareImage(type: SHARE_MEDIA, text: String?, image: UMImage) {
		umShareAction.shareImage(type, text, image)
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
		umShareAction.shareImages(type, text, *images)
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
		umShareAction.shareUrl(type, url, title, desc, image)
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
		umShareAction.shareVideo(type, url, title, desc, image)
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
		umShareAction.shareMusic(type, url, title, desc, image)
	}

	/**
	 * 网络图片资源
	 * @param url 图片Url
	 */
	override fun produceImage(url: String): UMImage {
		return umImageSource.produceImage(url)
	}

	/**
	 * 文件图片资源
	 * @param file 图片文件
	 */
	override fun produceImage(file: File): UMImage {
		return umImageSource.produceImage(file)
	}

	/**
	 * 资源文件资源
	 * @param imageRes 图片资源 resourceId
	 */
	override fun produceImage(@DrawableRes imageRes: Int): UMImage {
		return umImageSource.produceImage(imageRes)
	}

	/**
	 * Bitmap文件资源
	 * @param bitmap
	 */
	override fun produceImage(bitmap: Bitmap): UMImage {
		return umImageSource.produceImage(bitmap)
	}

	/**
	 * 字节流资源
	 * @param bytes 图片字节流
	 */
	override fun produceImage(bytes: ByteArray): UMImage {
		return umImageSource.produceImage(bytes)
	}
}