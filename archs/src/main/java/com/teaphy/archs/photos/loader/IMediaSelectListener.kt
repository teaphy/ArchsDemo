package com.teaphy.archs.photos.loader

import com.teaphy.archs.photos.entity.LocalMedia

/**
 * @desc
 * @author teaphy
 * @time 2018/10/11 下午1:47
 */
@FunctionalInterface
interface IMediaSelectListener {
	fun onSelected(listMedias: List<LocalMedia>)
}