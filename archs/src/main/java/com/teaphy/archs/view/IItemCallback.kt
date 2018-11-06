package com.teaphy.archs.view

/**
 * @desc
 * @author teaphy
 * @time 2018/8/28 上午10:19
 */
interface IItemCallback<in T> {
	fun onItemClick(item: T)
}