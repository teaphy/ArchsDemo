package com.teaphy.archs.photos.loader

import com.teaphy.archs.photos.entity.LocalMedia

/**
 * @desc
 * @author teaphy
 * @time 2018/10/12 下午2:59
 */
interface ITakePhotoListener {
	fun onTakePhoto(localMedia: LocalMedia?)
}