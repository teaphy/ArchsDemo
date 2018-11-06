package com.teaphy.archs.view

/**
 * @desc
 * @author teaphy
 * @time 2018/8/28 上午10:19
 */
interface IItemIndexedCallback<in T> {
	fun onItemClick(index: Int, item: T)
}