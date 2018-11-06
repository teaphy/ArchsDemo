package com.teaphy.archs.base

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * @desc
 * @author teaphy
 * @time 2018/5/22 下午3:23
 */
abstract class BaseDefineLinearLayout : LinearLayout {

	constructor(context: Context?) : super(context) {
		initConfig()
	}

	constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
		if (attrs != null) {
			this.initAttrs(attrs)
		}

		initConfig()
	}

	constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

		if (attrs != null) {
			this.initAttrs(attrs)
		}

		initConfig()
	}

	private fun initConfig() {

		initData()

		initView()

		setListener()
	}

	open fun initData() {

	}

	abstract fun initAttrs(attrs: AttributeSet)

	abstract fun initView()

	abstract fun setListener()
}