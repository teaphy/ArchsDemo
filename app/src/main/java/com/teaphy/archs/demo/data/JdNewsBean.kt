package com.teaphy.archs.demo.data

/**
 * @desc
 * @author teaphy
 * @time 2018/8/23 下午3:45
 */
class JdNewsBean(var status: Int = 0, var msg: String = "ok", var result: NewsChannelBean) {
	override fun toString(): String {
		return "JdNewsBean(status=$status, msg='$msg', result=$result)"
	}
}