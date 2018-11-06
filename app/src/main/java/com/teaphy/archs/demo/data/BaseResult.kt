package com.teaphy.archs.demo.data

/**
 * @desc
 * @author teaphy
 * @time 2018/8/23 下午3:36
 */
open class BaseResult(var code: String = "10000", var charge: Boolean = false, var msg: String = "查询成功") {

	override fun toString(): String {
		return "BaseResult(code='$code', charge=$charge, msg='$msg')"
	}
}

