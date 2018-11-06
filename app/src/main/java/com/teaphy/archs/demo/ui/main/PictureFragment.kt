package com.teaphy.archs.demo.ui.main


import com.blankj.utilcode.util.SizeUtils
import com.teaphy.archs.base.BaseFragment
import com.teaphy.archs.picture.PictureHelper

import com.teaphy.archs.demo.R
import com.teaphy.archs.demo.databinding.FragmentPictureBinding

/**
 * PictureUtils测试类
 */
class PictureFragment : BaseFragment<FragmentPictureBinding>() {


	override fun getLayoutId(): Int {
		return R.layout.fragment_picture
	}


	override fun initView() {
		super.initView()

	}


	override fun setListener() {
		super.setListener()

		val imageUrl = "http://a.hiphotos.baidu.com/image/pic/item/b3fb43166d224f4ad8b5722604f790529822d1d3.jpg"

		// 加载远程图片
		PictureHelper().loadRemoteImage(mBinding.iv1, imageUrl)
		PictureHelper().loadRemoteCircleImage(mBinding.iv2, imageUrl)
		PictureHelper().loadRemoteRoundImage(mBinding.iv3, imageUrl, SizeUtils.dp2px(16f))

		// 加载本地图片
		PictureHelper().loadLocalImage(mBinding.iv4, R.mipmap.ic_tree)
		PictureHelper().loadLocalCircleImage(mBinding.iv5, R.mipmap.ic_tree)
		PictureHelper().loadLocalRoundImage(mBinding.iv6, R.mipmap.ic_tree, SizeUtils.dp2px(16f))
	}
}
