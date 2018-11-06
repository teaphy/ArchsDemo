package com.teaphy.archs.location

/**
 * @desc 定位结果回调
 * @author teaphy
 * @time 2018/8/30 下午5:55
 */
@FunctionalInterface
interface ILocationCallback {
	fun onChange(locationResult: LocationResult?)
}