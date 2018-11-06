package com.teaphy.archs.photos.loader

import com.teaphy.archs.photos.entity.LocalMediaFolder

/**
 * @desc
 * @author teaphy
 * @time 2018/9/29 上午9:46
 */
@FunctionalInterface
interface ILocalMediaLoadListener {
	fun loadComplete(localImageFolders: List<LocalMediaFolder>)
}