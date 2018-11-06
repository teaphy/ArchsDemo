package com.teaphy.archs.widget

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.teaphy.archs.R
import com.teaphy.archs.picture.PictureHelper
import com.teaphy.archs.view.IBackView
import com.teaphy.archs.view.IFuncView

/**
 * @desc  带有返回和功能标签的 标题栏，其中图标为ImageView
 * @author teaphy
 * @time 2018/8/3 下午2:16
 */
class BackTitleImageView: LinearLayout {

	lateinit var root: View
	lateinit var ivBack: ImageView
	lateinit var tvTitle: TextView
	lateinit var ivFunc: ImageView
	var funcView: IFuncView? = null
	var backView: IBackView? = null

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
		root = LayoutInflater.from(context).inflate(R.layout.afcs_layout_pre_image_view, null, false)

		with(root) {
			ivBack = findViewById(R.id.ivBack)
			tvTitle = findViewById(R.id.tvTitle)
			ivFunc = findViewById(R.id.ivFunc)
		}

		setListener()

		addView(root)
	}


	private fun setListener() {

		ivBack.setOnClickListener{
			if (null != backView) {
				backView!!.killSelf()
			}
		}

		ivFunc.setOnClickListener {
			if (null != funcView) {
				funcView!!.doFunc()
			}
		}
	}

	private fun configureAttr(attrs: AttributeSet) {
		val attrArray = context.obtainStyledAttributes(attrs, R.styleable.BackTitleImageView)
				?: return

		with(attrArray) {
			// 默认高度48dp
			val rootHeight = attrArray.getDimension(R.styleable.BackTitleImageView_tvivBackHeight, 48f)
			val rootRes = attrArray.getResourceId(R.styleable.BackTitleImageView_tvivBackground, -1)
			with(root) {
				val lp = layoutParams as LinearLayout.LayoutParams
				lp.height = rootHeight.toInt()
				layoutParams = lp

				if (-1 != rootRes) {
					setBackgroundResource(rootRes)
				}
			}

		}

		attrArray.recycle()
	}


	fun setTitile(title: String) {
		tvTitle.text = title
	}

	fun setTitle(@StringRes titleRes: Int) {
		tvTitle.setText(titleRes)
	}

	fun setBackRes(@DrawableRes backRes: Int) {
		ivBack.setImageResource(backRes)
	}

	fun setBackRemote(urlBack: String) {
		PictureHelper().loadRemoteImage(ivBack, urlBack)
	}

	fun setFuncRes(@DrawableRes funcRes: Int) {
		ivFunc.setImageResource(funcRes)
	}

	fun setFuncRemote(urlFunc: String) {
		PictureHelper().loadRemoteImage(ivBack, urlFunc)
	}
}