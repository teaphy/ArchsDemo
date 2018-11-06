package com.teaphy.archs.utils

/**
 * @author teaphy
 * @desc
 * @time 2018/10/11 下午1:30
 */
class FastClickUtils {

	companion object {

		private var lastClickTime: Long = 0
		private val TIME: Long = 1000

		val isCanClick: Boolean
			get() {
				val time = System.currentTimeMillis()
				if (time - lastClickTime < TIME) {
					return false
				}
				lastClickTime = time
				return true
			}
	}


}

