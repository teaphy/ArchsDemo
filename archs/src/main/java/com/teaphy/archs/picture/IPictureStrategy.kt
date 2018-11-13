package com.teaphy.archs.picture

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.widget.ImageView

/**
 * @author teaphy
 * @desc 图片加载主体类
 * @time 2018/8/23 下午2:28
 */
interface IPictureStrategy {

	fun loadLocalImage(imageView: ImageView, path: String)

	fun loadLocalRoundImage(imageView: ImageView, radius: Int, path: String)

	fun loadLocalCircleImage(imageView: ImageView, path: String)

	fun loadLocalImage(imageView: ImageView, @DrawableRes imgRes: Int)

	fun loadLocalRoundImage(imageView: ImageView, @DrawableRes imgRes: Int, radius: Int)

	fun loadLocalCircleImage(imageView: ImageView, @DrawableRes imgRes: Int)

	fun loadRemoteImage(imageView: ImageView, urlImage: String)

	fun loadRemoteImage(imageView: ImageView, urlImage: String,
	                    @DrawableRes placeHolder: Int,@DrawableRes errorHolder: Int)

	fun loadRemoteImage(imageView: ImageView, urlImage: String,
						placeHolder: Drawable, errorHolder: Drawable)

	fun loadRemoteRoundImage(imageView: ImageView, urlImage: String, radius: Int)

	fun loadRemoteRoundImage(imageView: ImageView, urlImage: String, radius: Int,
	                         @DrawableRes placeHolder: Int,@DrawableRes errorHolder: Int)

	fun loadRemoteRoundImage(imageView: ImageView, urlImage: String, radius: Int,
							 placeHolder: Drawable, errorHolder: Drawable)

	fun loadRemoteCircleImage(imageView: ImageView, urlImage: String)

	fun loadRemoteCircleImage(imageView: ImageView, urlImage: String,
	                          @DrawableRes placeHolder: Int,@DrawableRes errorHolder: Int)
	fun loadRemoteCircleImage(imageView: ImageView, urlImage: String,
							  placeHolder: Drawable, errorHolder: Drawable)
}
