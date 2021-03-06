package com.teaphy.archs.photos.ui

import com.teaphy.archs.photos.config.PictureConfig
import com.teaphy.archs.photos.entity.LocalMedia

/**
 * @desc 选择图片 - 从本地Media数据库读取图片列表
 * @author teaphy
 * @time 2018/9/30 上午9:37
 */
class PhotosSelectByFolderActivity : BasePhotoSelectorActivity() {

	override fun setListener() {
		super.setListener()

		backText.setOnClickListener {
			finish()
		}
	}

	/**
	 * 从本地Media数据库读取图片列表
	 */
	override fun loadLocalImage() {
		val bundle = intent.extras

		val images = bundle.getParcelableArrayList<LocalMedia>(PictureConfig.KEY_CONTENT)

		images?.let {
			if (it.isNotEmpty()) {
				refreshMedia(it, false)
			}
		}
	}


}