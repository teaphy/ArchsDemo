package com.teaphy.archs.photos.listener

import com.teaphy.archs.photos.entity.LocalMedia

/**
 * @desc
 * @author teaphy
 * @time 2018/9/29 下午6:22
 */
@FunctionalInterface
interface ISelectChangeListener {
	fun onSelectChange(localMedia: LocalMedia)
}