package com.teaphy.archs.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.support.annotation.StringRes
import android.support.constraint.ConstraintLayout
import android.support.v4.app.ActivityCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.teaphy.archs.R

/**
 * @desc 自定义Preference布局,,其中图标资源使用IconFont加载
 *       1. 相关属性必须在XML中定义，比如图片大小、字体大小及颜色等
 *       2. 图片资源、标题或者描述的内容可以动态设置
 *       3. 分割线相关属性设置必须在XML中定义
 * @author teaphy
 * @time 2018/7/30 上午11:28
 */
class PreIconFontView : LinearLayout {

	lateinit var rootPiv: View
	lateinit var iftvLogo: IconFontTextView
	lateinit var tvTitle: TextView
	lateinit var tvDesc: TextView
	lateinit var iftvRight: IconFontTextView
	lateinit var viewUnderLine: View


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


	/**
	  * 初始化View
	  * @author teaphy
	  * @date 2018/7/30 下午1:47
	  */
	private fun initView(context: Context) {
		val view = LayoutInflater.from(context).inflate(R.layout.afcs_layout_pre_iconfont_view, null, false)

		with(view) {
			rootPiv = findViewById(R.id.root)
			iftvLogo = findViewById(R.id.iftvLogo)
			tvTitle = findViewById(R.id.tvTitle)
			tvDesc = findViewById(R.id.tvDesc)
			iftvRight = findViewById(R.id.iftvRight)
			viewUnderLine = findViewById(R.id.viewUnderLine)
		}

		addView(view)
	}

	/**
	  * 配置属性
	  * @author teaphy
	  * @date 2018/7/30 下午1:48
	  */
	private fun configureAttr(attrs: AttributeSet) {
		val attrArray = context.obtainStyledAttributes(attrs, R.styleable.PreIconFontView) ?: return

		with(attrArray) {
			// Root相关设置
			// 默认背景色为
			val pivBackground = attrArray.getColor(R.styleable.PreIconFontView_pifvBackground, ActivityCompat.getColor(context, android.R.color.white))
			// 默认宽度充满屏幕
			val pivWidth = attrArray.getDimension(R.styleable.PreIconFontView_pifvWidth, 0f)
			// 默认高度为48dp
			val pivHeight = attrArray.getDimension(R.styleable.PreIconFontView_pifvHeight, 48f)

			with(rootPiv) {
				setBackgroundColor(pivBackground)
				val lp = layoutParams
				lp.width = if (pivWidth == 0f) ViewGroup.LayoutParams.MATCH_PARENT else pivWidth.toInt()
				lp.height = pivHeight.toInt()
				layoutParams = lp
			}

			// 左侧Logo相关设置
			val logo = attrArray.getString(R.styleable.PreIconFontView_pifvLogo)
			// 默认图标大小为36sp
			val logoSize = attrArray.getInteger(R.styleable.PreIconFontView_pifvLogoSize, 36)
			// 默认图标颜色为红色
			val logoColor = attrArray.getColor(R.styleable.PreIconFontView_pifvLogoColor, ActivityCompat.getColor(context, android.R.color.holo_red_dark))
			// 默认的起始间距为14dp
			val logoStart = attrArray.getDimension(R.styleable.PreIconFontView_pifvLogoStart, 14f)
			// 默认显示
			val logoVisibility = attrArray.getBoolean(R.styleable.PreIconFontView_pifvLogoVisibility, true)
			// 默认IconFont宽度为36px
			val logoWidth = attrArray.getDimension(R.styleable.PreIconFontView_pifvLogoWidth, 36f)

			with(iftvLogo) {
				if (logoVisibility) {
					visibility = View.VISIBLE
					text = logo
					textSize = logoSize.toFloat()
					setTextColor(logoColor)
					val lp = layoutParams as ViewGroup.MarginLayoutParams
					lp.width = logoWidth.toInt()
					lp.marginStart = logoStart.toInt()
					layoutParams = lp
				} else {
					visibility = View.GONE
				}

			}

			// 标题相关设置
			val title = attrArray.getString(R.styleable.PreIconFontView_pifvTitle)
			// 默认字体大小为为14sp
			val titleSize = attrArray.getInteger(R.styleable.PreIconFontView_pifvTitleSize, 14)
			// 默认字体颜色 #AAAAAA
			val titleColor = attrArray.getColor(R.styleable.PreIconFontView_pifvTitleColor, ActivityCompat.getColor(context, android.R.color.darker_gray))
			// 默认间距为18px
			val titleStart = attrArray.getDimension(R.styleable.PreIconFontView_pifvTitleStart, 18f)

			with(tvTitle) {
				text = title
				textSize = titleSize.toFloat()
				setTextColor(titleColor)
				if (logoVisibility) {
					val lp = layoutParams as ViewGroup.MarginLayoutParams
					lp.marginStart = titleStart.toInt()
					layoutParams = lp
				} else {
					val lp = layoutParams as ConstraintLayout.LayoutParams
					lp.goneStartMargin = titleStart.toInt()
					layoutParams = lp
				}
			}

			// 右侧图标相关设置
			val right = attrArray.getString(R.styleable.PreIconFontView_pifvRight)
			// 默认图标大小为36sp
			val rightSize = attrArray.getInteger(R.styleable.PreIconFontView_pifvRightSize, 36)
			// 默认图标颜色为红色
			val rightColor = attrArray.getColor(R.styleable.PreIconFontView_pifvRightColor, ActivityCompat.getColor(context, android.R.color.holo_red_dark))
			// 默认的起始间距为42px
			val rightEnd = attrArray.getDimension(R.styleable.PreIconFontView_pifvRightEnd, 42f)
			// 默认显示
			val rightVisible = attrArray.getBoolean(R.styleable.PreIconFontView_pifvRightVisible, true)
			val pifvRightWidth = attrArray.getDimension(R.styleable.PreIconFontView_pifvRightWidth, 16f)

			// 显示右侧图标
			if (rightVisible) {
				with(iftvRight) {
					text = right
					textSize = rightSize.toFloat()
					setTextColor(rightColor)
					val lp = layoutParams as ViewGroup.MarginLayoutParams
					lp.width = pifvRightWidth.toInt()
					lp.marginEnd = rightEnd.toInt()
					layoutParams = lp
					visibility = View.VISIBLE
				}
			} else {
				// 不显示右侧图标
				iftvRight.visibility = View.GONE
			}

			// 设置描述
			val desc = attrArray.getString(R.styleable.PreIconFontView_pifvDesc)
			// 默认字体大小为12sp
			val descSize = attrArray.getInteger(R.styleable.PreIconFontView_pifvDescSize, 12)
			// 默认字体颜色  #AAAAAA
			val descColor = attrArray.getColor(R.styleable.PreIconFontView_pifvDescColor, ActivityCompat.getColor(context, android.R.color.darker_gray))
			// 默认间距为18px
			val descEnd = attrArray.getDimension(R.styleable.PreIconFontView_pifvDescEnd, 18f)

			with(tvDesc) {
				text = desc
				textSize = descSize.toFloat()
				setTextColor(descColor)

				if (rightVisible) {
					val lp = layoutParams  as ViewGroup.MarginLayoutParams
					lp.marginEnd = descEnd.toInt()
					layoutParams = lp
				} else {
					val lp = layoutParams as ConstraintLayout.LayoutParams
					lp.goneEndMargin = descEnd.toInt()
					layoutParams = lp
				}
			}

			// 设置分割线
			// 默认高度1dp
			val lineHeight = attrArray.getDimension(R.styleable.PreIconFontView_pifvUnderLineHeight, 1f)
			// 默认背景颜色  #AAAAAA
			val lineColor = attrArray.getColor(R.styleable.PreIconFontView_pifvUnderLineColor, ActivityCompat.getColor(context, android.R.color.darker_gray))
			// 默认起始和结束位置的间距
			val lineStart = attrArray.getDimension(R.styleable.PreIconFontView_pifvUnderLineStart, 0f)
			val lineEnd = attrArray.getDimension(R.styleable.PreIconFontView_pifvUnderLineEnd, 0f)
			val lineVisible = attrArray.getBoolean(R.styleable.PreIconFontView_pifvUnderLineVisible, true)


			if (lineVisible) {
				with(viewUnderLine) {
					// 设置高度
					val lp = layoutParams
					lp.height = lineHeight.toInt()
					layoutParams = lp

					// 设置间距
					val lpMar = layoutParams  as ViewGroup.MarginLayoutParams
					lpMar.marginStart = lineStart.toInt()
					lpMar.marginEnd = lineEnd.toInt()
					layoutParams = lpMar

					// 设置背景颜色
					setBackgroundColor(lineColor)
					visibility = View.VISIBLE
				}
			} else {
				viewUnderLine.visibility = View.GONE
			}
		}

		attrArray.recycle()
	}

	/**
	 * 设置Logo
	 * @author teaphy
	 * @date 2018/7/30 下午4:04
	 */
	fun loadLogo(@StringRes logoRes: Int) {
		iftvLogo.setText(logoRes)
	}

	fun loadLogo(logo: String) {
		iftvLogo.text = logo
	}

	/**
	  * 设置标题
	  * @author teaphy
	  * @date 2018/7/30 下午4:04
	  */
	fun setTitle(title: String) {
		tvTitle.text = title
	}

	fun setTitle(@StringRes titleRes: Int) {
		tvTitle.setText(titleRes)
	}

	/**
	  * 设置描述
	  * @author teaphy
	  * @date 2018/7/30 下午4:04
	  */
	fun setDesc(desc: String) {
		tvDesc.text = desc
	}

	fun setDesc(@StringRes descRes: Int) {
		tvDesc.setText(descRes)
	}

	fun setDesc(charSequence: CharSequence) {
		tvDesc.text = charSequence
	}

	/**
	 * 设置右侧图标
	 * @author teaphy
	 * @date 2018/7/30 下午4:04
	 */
	fun loadRight(@StringRes rightRes: Int) {
		iftvRight.setText(rightRes)
	}

	fun loadtRight(right: String) {
		iftvRight.text = right
	}

	/**
	 * 右侧图标 - 执行旋转
	 *
	 * @param start 旋转开始角度
	 * @param end   旋转结束角度
	 */
	fun executeRotation(start: Float, end: Float) {
		val animator = ObjectAnimator.ofFloat(iftvRight, "rotation", start, end)

		animator.apply{
			duration = 500
			start()
		}

	}

	fun executeRotation(start: Float, end: Float, dura: Long) {
		val animator = ObjectAnimator.ofFloat(iftvRight, "rotation", start, end)

		animator.apply{
			duration = dura
			start()
		}

	}
}