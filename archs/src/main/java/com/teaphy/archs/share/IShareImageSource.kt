package com.teaphy.archs.share

import android.app.Activity
import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import com.umeng.socialize.media.UMImage
import java.io.File

/**
 * @desc
 * @author teaphy
 * @time 2018/8/30 下午3:41
 */
interface IShareImageSource {

	/**
	 * 网络图片资源
	 * @param url 图片Url
	 */
	fun produceImage(url: String): UMImage

	/**
	 * 文件图片资源
	 * @param file 图片文件
	 */
	fun produceImage(file: File): UMImage

	/**
	 * 资源文件资源
	 * @param imageRes 图片资源 resourceId
	 */
	fun produceImage(@DrawableRes imageRes: Int): UMImage

	/**
	 * Bitmap文件资源
	 * @param bitmap
	 */
	fun produceImage(bitmap: Bitmap): UMImage

	/**
	 * 字节流资源
	 * @param bytes 图片字节流
	 */
	fun produceImage(bytes: ByteArray): UMImage
}