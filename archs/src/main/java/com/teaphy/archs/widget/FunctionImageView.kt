package com.teaphy.archs.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.teaphy.archs.R
import com.teaphy.archs.base.BaseDefineLinearLayout
import com.teaphy.archs.picture.PictureHelper

/**
 * @desc IconFont(Logo) + TextView(描述)
 * @author teaphy
 * @time 2018/5/22 下午3:21
 */
class FunctionImageView @JvmOverloads constructor(
		context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseDefineLinearLayout(context, attrs, defStyleAttr) {

	lateinit var mLogo: ImageView
	lateinit var mDesc: TextView

	var logoRes: Int
	var logoWidth: Float
	var logoHeight: Float
	var logoType : Int // 图片加载方式 1：普通加载，默认 2：圆形图片 3：带有边角有弧度的图片
	var logoRoundRadius: Float
	var descContent: String
	var descSize: Int
	var descColor: Int
	var spaceBetween: Float

	init {
		logoRes = R.drawable.ic_launcher
		logoWidth = 16f
		logoHeight = 16f
		descContent = ""
		descSize = 14
		descColor = ContextCompat.getColor(context, android.R.color.holo_red_dark)
		spaceBetween = 6f
		logoType = 1
		logoRoundRadius = 0f
	}

	override fun initData() {
		orientation = LinearLayout.VERTICAL
		gravity = Gravity.CENTER
		mLogo = ImageView(context)
		mDesc = TextView(context)
	}

	override fun initView() {


		addView(mLogo)
		addView(mDesc)

		displayImage()

		displayContent()

		val params = LinearLayout.LayoutParams(mDesc.layoutParams)
		params.setMargins(0, spaceBetween.toInt(), 0, 0)
		mDesc.layoutParams = params
	}

	override fun setListener() {
	}

	override fun initAttrs(attrs: AttributeSet) {
		val attrArray = context.obtainStyledAttributes(attrs, R.styleable.FunctionImageView) ?: return

		with(attrArray) {
			logoRes = attrArray.getResourceId(R.styleable.FunctionImageView_logoRes, R.drawable.ic_launcher)
			logoWidth = attrArray.getDimension(R.styleable.FunctionImageView_logoWidth, 16f)
			logoHeight = attrArray.getDimension(R.styleable.FunctionImageView_logoHeight, 16f)
			logoRoundRadius = attrArray.getDimension(R.styleable.FunctionImageView_logoRoundRadius, 6f)
			logoType = attrArray.getInt(R.styleable.FunctionImageView_logoType, 1)

			descContent = attrArray.getString(R.styleable.FunctionImageView_fivContent)
			descSize = attrArray.getInt(R.styleable.FunctionImageView_fivSize, 14)
			descColor = attrArray.getColor(R.styleable.FunctionImageView_fivColor, ContextCompat.getColor(context, android.R.color.holo_red_dark))
			spaceBetween = attrArray.getDimension(R.styleable.FunctionImageView_fivBetween, 8f)
			recycle()
		}


	}

	// 显示图片
	private fun displayImage() {
		with(mLogo) {
			when (logoType) {
				1 -> PictureHelper().loadLocalImage(this, logoRes)
				2 -> PictureHelper().loadLocalCircleImage(this, logoRes)
				3 -> PictureHelper().loadLocalRoundImage(this, logoRes, logoRoundRadius.toInt())
			}
			val params = LinearLayout.LayoutParams(layoutParams)
			params.width = logoWidth.toInt()
			params.height = logoHeight.toInt()
			layoutParams = params
		}
	}

	private fun displayContent() {
		with(mDesc) {
			gravity = Gravity.CENTER
			text = descContent
			setTextColor(descColor)
			textSize = descSize.toFloat()
		}
	}
}