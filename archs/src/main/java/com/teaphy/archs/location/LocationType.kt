package com.teaphy.archs.location

/**
 * @desc  定位类型
 * @author teaphy
 * @time 2018/8/31 下午3:31
 */
enum class LocationType {
	FAILURE, // 定位失败,错误码:errorCode
	LOCATION_GPS, // GPS定位结果,通过设备GPS定位模块返回的定位结果，精度较高，在10米－100米左右
	LOCATION_PRE, // 前次定位结果, 网络定位请求低于1秒、或两次定位之间设备位置变化非常小时返回，设备位移通过传感器感知。
	LOCATION_CACHE, // 缓存定位结果 , 返回一段时间前设备在同样的位置缓存下来的网络定位结果
	LOCATION_WIFI, // Wifi定位结果, 属于网络定位，定位精度相对基站定位会更好，定位精度较高，在5米－200米之间。
	LOCATION_BS, // 基站定位结果, 纯粹依赖移动、联通、电信等移动网络定位，定位精度在500米-5000米之间。
	LOCATION_OFFLINE // 离线定位结果
}