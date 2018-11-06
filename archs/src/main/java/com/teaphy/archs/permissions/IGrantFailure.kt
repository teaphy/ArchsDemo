package com.teaphy.archs.permissions

/**
 * @desc 未授权
 * @author teaphy
 * @time 2018/7/28 下午4:10
 */
@FunctionalInterface
interface IGrantedFailure {
	fun onGrantedFailure()
}