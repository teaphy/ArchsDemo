package com.teaphy.archs.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.teaphy.archs.R
import com.teaphy.archs.base.BaseDefineLinearLayout

/**
 * @desc IconFont(Logo) + TextView(描述)
 * @author teaphy
 * @time 2018/5/22 下午3:21
 */
class FunctionFontView @JvmOverloads constructor(
		context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseDefineLinearLayout(context, attrs, defStyleAttr) {

	private lateinit var mLogo: IconFontTextView
	private lateinit var mDesc: TextView

	var logoIcon: String
	var logoColor: Int
	var logoSize: Int
	var descContent: String
	var descSize: Int
	var descColor: Int
	var spaceBetween: Float

	init {
		logoIcon = ""
		logoColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
		logoSize = 16
		descContent = ""
		descSize = 14
		descColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
		spaceBetween = 6f

	}

	override fun initData() {
		orientation = LinearLayout.VERTICAL
		gravity = Gravity.CENTER
		mLogo = IconFontTextView(context)
		mDesc = TextView(context)
	}

	override fun initView() {

		addView(mLogo)
		addView(mDesc)

		displayContent(mLogo, logoIcon, logoColor, logoSize)

		displayContent(mDesc, descContent, descColor, descSize)

		val params = LinearLayout.LayoutParams(mDesc.layoutParams)
		params.setMargins(0, spaceBetween.toInt(), 0, 0)
		mDesc.layoutParams = params
	}

	override fun setListener() {
	}

	override fun initAttrs(attrs: AttributeSet) {
		val attrArray = context.obtainStyledAttributes(attrs, R.styleable.FunctionFontView)
				?: return

		with(attrArray) {
			logoIcon = attrArray.getString(R.styleable.FunctionFontView_logoIcon)
			logoColor = attrArray.getColor(R.styleable.FunctionFontView_logoColor, ContextCompat.getColor(context, android.R.color.holo_red_dark))
			logoSize = attrArray.getInt(R.styleable.FunctionFontView_logoSize, 16)
			descContent = attrArray.getString(R.styleable.FunctionFontView_descContent)
			descSize = attrArray.getInt(R.styleable.FunctionFontView_descSize, 14)
			descColor = attrArray.getColor(R.styleable.FunctionFontView_descColor, ContextCompat.getColor(context, android.R.color.holo_red_dark))
			spaceBetween = attrArray.getDimension(R.styleable.FunctionFontView_spaceBetween, 8f)

			recycle()
		}


	}

	private fun displayContent(textView: TextView, content: String, colorText: Int, sizeText: Int) {
		with(textView) {
			gravity = Gravity.CENTER
			text = content
			setTextColor(colorText)
			textSize = sizeText.toFloat()
		}
	}
}