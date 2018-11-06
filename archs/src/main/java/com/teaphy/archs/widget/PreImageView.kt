package com.teaphy.archs.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.constraint.ConstraintLayout
import android.support.v4.app.ActivityCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.teaphy.archs.R
import com.teaphy.archs.picture.PictureHelper

/**
 * @desc 自定义Preference布局,其中图标资源使用ImageView加载
 *       1. 相关属性必须在XML中定义，比如图片大小、字体大小及颜色等
 *       2. 图片资源、标题或者描述的内容可以动态设置
 *       3. 分割线相关属性设置必须在XML中定义
 * @author teaphy
 * @time 2018/7/30 上午11:28
 */
class PreImageView : LinearLayout {

	lateinit var rootPiv: View
	lateinit var ivLogo: ImageView
	lateinit var tvTitle: TextView
	lateinit var tvDesc: TextView
	lateinit var ivRight: ImageView
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
		val view = LayoutInflater.from(context).inflate(R.layout.afcs_layout_pre_image_view, null, false)

		with(view) {
			rootPiv = findViewById(R.id.root)
			ivLogo = findViewById(R.id.ivLogo)
			tvTitle = findViewById(R.id.tvTitle)
			tvDesc = findViewById(R.id.tvDesc)
			ivRight = findViewById(R.id.ivRight)
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
		val attrArray = context.obtainStyledAttributes(attrs, R.styleable.PreImageView) ?: return

		with(attrArray) {
			// Root相关设置
			// 默认背景色为
			val pivBackground = attrArray.getColor(R.styleable.PreImageView_pivBackground, ActivityCompat.getColor(context, android.R.color.white))
			// 默认宽度充满屏幕
			val pivWidth = attrArray.getDimension(R.styleable.PreImageView_pivWidth, 0f)
			// 默认高度为48dp
			val pivHeight = attrArray.getDimension(R.styleable.PreImageView_pivHeight, 48f)

			with(rootPiv) {
				setBackgroundColor(pivBackground)
				val lp = layoutParams
				lp.width = if (pivWidth == 0f) ViewGroup.LayoutParams.MATCH_PARENT else pivWidth.toInt()
				lp.height = pivHeight.toInt()
				layoutParams = lp
			}

			// 左侧Logo相关设置
			val logoRes = attrArray.getResourceId(R.styleable.PreImageView_pivLogoRes, R.drawable.ic_launcher)
			// 默认高度和宽度为32dp
			val logoWidth = attrArray.getDimension(R.styleable.PreImageView_pivLogoWidth, 32f)
			val logoHeight = attrArray.getDimension(R.styleable.PreImageView_pivLogoHeight, 32f)
			val logoStart = attrArray.getDimension(R.styleable.PreImageView_pivLogoStart, 42f)
			// 默认显示
			val logoVisibility = attrArray.getBoolean(R.styleable.PreImageView_pivLogoVisibility, true)


			with(ivLogo) {
				if (logoVisibility) {
					visibility = View.VISIBLE
					setImageResource(logoRes)
					val lp = layoutParams as ViewGroup.MarginLayoutParams
					lp.width = logoWidth.toInt()
					lp.height = logoHeight.toInt()
					lp.marginStart = logoStart.toInt()
					layoutParams = lp
				} else {
					visibility = View.GONE
				}

			}

			// 标题相关设置
			val title = attrArray.getString(R.styleable.PreImageView_pivTitle)
			// 默认字体大小为为14dp
			val titleSize = attrArray.getInteger(R.styleable.PreImageView_pivTitleSize, 14)
			// 默认字体颜色 #AAAAAA
			val titleColor = attrArray.getColor(R.styleable.PreImageView_pivTitleColor, ActivityCompat.getColor(context, android.R.color.darker_gray))
			// 默认间距为6dp
			val titleStart = attrArray.getDimension(R.styleable.PreImageView_pivTitleStart, 6f)

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

			// 设置右侧图标
			// 左侧Logo相关设置
			val rightRes = attrArray.getResourceId(R.styleable.PreImageView_pivRightRes, R.drawable.ic_launcher)
			// 默认高度和宽度为32dp
			val rightWidth = attrArray.getDimension(R.styleable.PreImageView_pivRightWidth, 32f)
			val rightHeight = attrArray.getDimension(R.styleable.PreImageView_pivRightHeight, 32f)
			// 默认显示
			val rightVisible = attrArray.getBoolean(R.styleable.PreImageView_pivRightVisible, true)

			// 显示右侧图标
			if (rightVisible) {
				with(ivRight) {
					setImageResource(rightRes)
					val lp = layoutParams
					lp.width = rightWidth.toInt()
					lp.height = rightHeight.toInt()
					layoutParams = lp
					visibility = View.VISIBLE
				}
			} else {
				// 不显示右侧图标
				ivRight.visibility = View.GONE
			}

			// 设置描述
			val desc = attrArray.getString(R.styleable.PreImageView_pivDesc)
			// 默认字体大小为12sp
			val descSize = attrArray.getInteger(R.styleable.PreImageView_pivDescSize, 12)
			// 默认字体颜色  #AAAAAA
			val descColor = attrArray.getColor(R.styleable.PreImageView_pivDescColor, ActivityCompat.getColor(context, android.R.color.darker_gray))
			// 默认间距为6dp
			val descEnd = attrArray.getDimension(R.styleable.PreImageView_pivDescEnd, 6f)

			with(tvDesc) {
				text = desc
				textSize = descSize.toFloat()
				setTextColor(descColor)

				if (rightVisible) {
					val lp = layoutParams as ViewGroup.MarginLayoutParams
					lp.marginStart = descEnd.toInt()
					layoutParams = lp
				} else {
					val lp = layoutParams as ConstraintLayout.LayoutParams
					lp.goneEndMargin = descEnd.toInt()
					layoutParams = lp
				}
			}

			// 设置分割线
			// 默认高度1dp
			val lineHeight = attrArray.getDimension(R.styleable.PreImageView_pivUnderLineHeight, 1f)
			// 默认背景颜色  #AAAAAA
			val lineColor = attrArray.getColor(R.styleable.PreImageView_pivUnderLineColor, ActivityCompat.getColor(context, android.R.color.darker_gray))
			// 默认起始和结束位置的间距
			val lineStart = attrArray.getDimension(R.styleable.PreImageView_pivUnderLineStart, 0f)
			val lineEnd = attrArray.getDimension(R.styleable.PreImageView_pivUnderLineEnd, 0f)
			val lineVisible = attrArray.getBoolean(R.styleable.PreImageView_pivUnderLineVisible, true)


			if (lineVisible) {
				with(viewUnderLine) {
					// 设置高度
					val lp = layoutParams
					lp.height = lineHeight.toInt()
					layoutParams = lp

					// 设置间距
					val lpMar = layoutParams as ViewGroup.MarginLayoutParams
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
	fun loadLogo(@DrawableRes logoRes: Int) {
		ivLogo.setImageResource(logoRes)
	}

	fun loadLogo(logoUrl: String) {
		PictureHelper().loadRemoteImage(ivLogo, logoUrl)
	}

	fun loadLocalCircleLogo(@DrawableRes logoRes: Int) {
		PictureHelper().loadLocalCircleImage(ivLogo, logoRes)
	}

	fun loadCircleLogo(logoUrl: String) {
		PictureHelper().loadRemoteCircleImage(ivLogo, logoUrl)
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
	fun loadRight(@DrawableRes rightRes: Int) {
		ivRight.setImageResource(rightRes)
	}

	fun loadtRight(rightUrl: String) {
		PictureHelper().loadRemoteImage(ivRight, rightUrl)
	}

	/**
	 * 右侧图标 - 执行旋转
	 *
	 * @param start 旋转开始角度
	 * @param end   旋转结束角度
	 */
	fun executeRotation(start: Float, end: Float) {
		val animator = ObjectAnimator.ofFloat(ivRight, "rotation", start, end)

		animator.apply {
			duration = 500
			start()
		}

	}

	fun executeRotation(start: Float, end: Float, dura: Long) {
		val animator = ObjectAnimator.ofFloat(ivRight, "rotation", start, end)

		animator.apply {
			duration = dura
			start()
		}

	}
}