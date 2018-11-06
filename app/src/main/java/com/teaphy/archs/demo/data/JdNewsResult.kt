package com.teaphy.archs.demo.data


class JdNewsResult(var result: JdNewsBean?) : BaseResult() {
	override fun toString(): String {
		return super.toString() + " | JdNewsResult(result=$result)"
	}
}