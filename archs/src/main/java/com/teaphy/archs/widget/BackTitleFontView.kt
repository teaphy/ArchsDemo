package com.teaphy.archs.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * @desc 带有返回和功能标签的 标题栏，其中图标为IconFont
 * @author teaphy
 * @time 2018/8/3 下午2:16F
 */
class BackTitleFontView: LinearLayout{

	constructor(context: Context) : super(context) {
		initView(context)
	}

	constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
		initView(context)

		if (attrs != null) {
			configureAttr(attrs)
		}
	}

	constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
		initView(context)

		if (attrs != null) {
			configureAttr(attrs)
		}
	}

	private fun initView(context: Context) {

	}

	private fun configureAttr(attrs: AttributeSet) {

	}
}