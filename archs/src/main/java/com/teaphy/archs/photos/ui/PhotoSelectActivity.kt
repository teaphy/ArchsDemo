package com.teaphy.archs.photos.ui

import android.content.Intent
import com.teaphy.archs.R
import com.teaphy.archs.photos.entity.LocalMediaFolder
import com.teaphy.archs.photos.loader.ILocalMediaLoadListener
import com.teaphy.archs.photos.loader.LocalMediaLoader
import timber.log.Timber

/**
 * @desc 选择图片 - 从本地Media数据库读取图片列表
 * @author teaphy
 * @time 2018/9/30 上午9:37
 */
class PhotoSelectActivity : BasePhotoSelectorActivity() {

	override fun setListener() {
		super.setListener()

		backText.setOnClickListener {
			openPhotoFolderActivity()
			finish()
		}
	}

	/**
	 * 从本地Media数据库读取图片列表
	 */
	override fun loadLocalImage() {
		LocalMediaLoader(this, false)
				.loadAllMedia(object : ILocalMediaLoadListener {
					override fun loadComplete(localImageFolders: List<LocalMediaFolder>) {
						val images = localImageFolders[0].images

						Timber.e("images:$images")

						refreshMedia(images, true)
					}
				})
	}

	/**
	 * 跳转到 本地图片文件夹列表
	 */
	private fun openPhotoFolderActivity() {
		val intent = Intent(this, PhotoFoldersActivity::class.java)
		startActivity(intent)
	}
}