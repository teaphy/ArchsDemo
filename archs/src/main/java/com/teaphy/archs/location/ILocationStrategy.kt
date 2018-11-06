package com.teaphy.archs.location

import android.content.Context

/**
 * @desc 定位策略
 * @author teaphy
 * @time 2018/8/30 下午4:44
 */
interface ILocationStrategy {

	/**
	 * 初始化定位配置
	 */
	fun initLocation(context: Context, isSingle: Boolean, intervalLocation: Long = 1000L,
	                 locationCallback: ILocationCallback? = null)

	/**
	 * 开启定位
	 */
	fun startLocation()

	/**
	 * 停止定位
	 */
	fun stopLocation()

	/**
	 * 销毁定位客户端之后，
	 * 若要重新开启定位请重新New一个AMapLocationClient对象
	 */
	fun destroyClient()
}