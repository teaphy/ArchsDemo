package com.teaphy.archs.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.util.AttributeSet
import com.teaphy.archs.config.ArchConfig

/**
 * 适配IconFont的TextView
 */
class IconFontTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : android.support.v7.widget.AppCompatTextView(context, attrs, defStyleAttr) {

	private var mAnimator: ObjectAnimator? = null

	init {
		initFont()
	}

	private fun initFont() {
		// 如果配置IconFont文件，将采用IconFont字体
		// 否则为普通的TextView
		if (!TextUtils.isEmpty(ArchConfig.NAME_FILE_ICON_FONT)) {
			val typeface = Typeface.createFromAsset(context.applicationContext.assets,
					ArchConfig.NAME_FILE_ICON_FONT)
			setTypeface(typeface)
		}
	}

	/**
	 * 执行旋转
	 *
	 * @param start 旋转开始角度
	 * @param end   旋转结束角度
	 */
	fun executeRotation(start: Float, end: Float) {
		mAnimator = ObjectAnimator.ofFloat(this, "rotation", start, end)

		mAnimator?.apply{
			duration = 500
			start()
		}

	}
}
