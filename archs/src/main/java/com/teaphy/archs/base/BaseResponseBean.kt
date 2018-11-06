package com.teaphy.archs.base

open class BaseResponseBean(val success: Boolean, val msg: String, val error: Int) {
	constructor() : this(false, "", 0)
}
