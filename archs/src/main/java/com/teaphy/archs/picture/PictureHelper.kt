package com.teaphy.archs.picture

import android.widget.ImageView

/**
 * @desc 图片加载 代理类
 * @author teaphy
 * @time 2018/7/28 下午3:26
 * @param pictureProcessor 图片默认加载策略为GlideStrategy
 */
class PictureHelper constructor(private var pictureProcessor : IPictureStrategy = GlideStrategy()) : IPictureStrategy{

	override fun loadLocalImage(imageView: ImageView, path: String) {
		pictureProcessor.loadLocalImage(imageView, path)
	}

	override fun loadLocalRoundImage(imageView: ImageView, radius: Int, path: String) {
		pictureProcessor.loadLocalRoundImage(imageView, radius, path)
	}

	override fun loadLocalCircleImage(imageView: ImageView, path: String) {
		pictureProcessor.loadLocalCircleImage(imageView, path)
	}


	override fun loadLocalImage(imageView: ImageView, imgRes: Int) {
		pictureProcessor.loadLocalImage(imageView, imgRes)
	}

	override fun loadLocalRoundImage(imageView: ImageView, imgRes: Int, radius: Int) {
		pictureProcessor.loadLocalRoundImage(imageView, imgRes, radius)
	}

	override fun loadLocalCircleImage(imageView: ImageView, imgRes: Int) {
		pictureProcessor.loadLocalCircleImage(imageView, imgRes)
	}

	override fun loadRemoteImage(imageView: ImageView, urlImage: String) {
		pictureProcessor.loadRemoteImage(imageView, urlImage)
	}

	override fun loadRemoteImage(imageView: ImageView, urlImage: String, placeHolder: Int, errorHolder: Int) {
		pictureProcessor.loadRemoteImage(imageView, urlImage, placeHolder, errorHolder)
	}

	override fun loadRemoteRoundImage(imageView: ImageView, urlImage: String, radius: Int) {
		pictureProcessor.loadRemoteRoundImage(imageView, urlImage, radius)
	}

	override fun loadRemoteRoundImage(imageView: ImageView, urlImage: String, radius: Int, placeHolder: Int, errorHolder: Int) {
		pictureProcessor.loadRemoteRoundImage(imageView, urlImage, radius, placeHolder, errorHolder)
	}

	override fun loadRemoteCircleImage(imageView: ImageView, urlImage: String) {
		pictureProcessor.loadRemoteCircleImage(imageView, urlImage)
	}

	override fun loadRemoteCircleImage(imageView: ImageView, urlImage: String, placeHolder: Int, errorHolder: Int) {
		pictureProcessor.loadRemoteCircleImage(imageView, urlImage, placeHolder, errorHolder)
	}
}