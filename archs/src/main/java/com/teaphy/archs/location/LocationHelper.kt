package com.teaphy.archs.location

import android.content.Context

/**
 * @desc 定位工具类
 * @author teaphy
 * @time 2018/8/30 下午6:04
 * @param locationStrategy 定位默认策略为 AMapStrategy(高德地图)
 */
class LocationHelper constructor(private val locationStrategy: ILocationStrategy = AMapStrategy()): ILocationStrategy {

	/**
	 * 初始化定位
	 */
	override fun initLocation(context: Context, isSingle: Boolean, intervalLocation: Long, locationCallback: ILocationCallback?) {
		locationStrategy.initLocation(context, isSingle, intervalLocation, locationCallback)
	}

	/**
	 * 开启定位
	 */
	override fun startLocation() {
		locationStrategy.startLocation()
	}

	/**
	 * 停止定位
	 */
	override fun stopLocation() {
		locationStrategy.stopLocation()
	}

	/**
	 * 销毁定位客户端之后，
	 * 若要重新开启定位请重新New一个AMapLocationClient对象
	 */
	override fun destroyClient() {
		locationStrategy.destroyClient()
	}
}