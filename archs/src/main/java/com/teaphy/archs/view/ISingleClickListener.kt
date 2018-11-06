package com.teaphy.archs.view

import android.view.View
import com.teaphy.archs.utils.FastClickUtils

/**
 * @desc 避免单击时间间隔过短，变多击(间隔时间为1秒)
 * @author teaphy
 * @time 2018/10/23 下午3:44
 */
interface ISingleClickListener : View.OnClickListener {

	override fun onClick(v: View?) {
		if (FastClickUtils.isCanClick) {
			accept(view = v)
		}
	}

	fun accept(view: View?)
}