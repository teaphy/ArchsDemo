package com.teaphy.archs.base

import android.arch.lifecycle.Observer
import android.databinding.ViewDataBinding
import com.teaphy.archs.view.ILoadingView

/**
 * @desc 带有ViewModel的Fragment基类
 * @author teaphy
 * @time 2018/5/28 下午3:45
 */
abstract class BaseVmFragment<P: ViewDataBinding, S :BaseViewModel>: BaseFragment<P>(), ILoadingView {

	protected lateinit var mViewModel: S

	override fun initData() {
		super.initData()

		mViewModel = initViewModel()

		subscribeObserve()
	}

	/**
	 * 初始化ViewModel
	 * @author teaphy
	 * @date 2018/7/23 上午10:31
	 */
	abstract fun initViewModel(): S

	open fun subscribeObserve() {
		mViewModel.getLoadingLiveData().observe(this, Observer {
			it?.apply {
				if (it) {
					// 显示加载中Dialog
					showLoading()
				} else {
					// 隐藏加载中Dialog
					hideLoading()
				}
			}
		})
	}
}