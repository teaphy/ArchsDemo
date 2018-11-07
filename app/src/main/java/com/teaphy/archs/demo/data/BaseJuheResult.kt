package com.teaphy.archs.demo.data

open class BaseJuheResult(var reason: String = "成功的返回", var error_code: Int = 0) {

    override fun toString(): String {
        return "BaseResult(reason='$reason', error_code=$error_code)"
    }
}

