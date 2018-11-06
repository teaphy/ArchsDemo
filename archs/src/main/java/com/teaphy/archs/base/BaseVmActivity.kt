package com.teaphy.archs.base

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.pm.PackageManager
import android.databinding.ViewDataBinding
import android.support.v4.app.DialogFragment
import com.teaphy.archs.view.ILoadingView

/**
 * @desc 带有ViewModel的Activity基类
 * @author teaphy
 * @time 2018/5/28 下午3:45
 */
abstract class BaseVmActivity<P: ViewDataBinding, S :BaseViewModel> : BaseActivity<P>(), ILoadingView {
	protected lateinit var mViewModel: S

	override fun initData() {
		super.initData()

		mViewModel = initViewModel()

		subscribeObserve()
	}

	/**
	 * 添加Observer订阅
	 */
	open fun subscribeObserve() {
		subscribeLoadingObserver(mViewModel)
	}

	/**
	 * 显示加载中Dialog
	 */
	override fun showLoading() {

	}

	/**
	 * 隐藏LoadingDialog
	 */
	override fun hideLoading() {

	}

	/**
	 * 添加Loading订阅
	 */
	protected fun <T:BaseViewModel> subscribeLoadingObserver(viewModel: T) {
		viewModel.getLoadingLiveData().observe(this, Observer {
			it?.apply {
				if (it) {
					// 显示LoadingDialog
					showLoading()
				} else {
					// 隐藏LoadingDialog
					hideLoading()
				}
			}
		})
	}

	/**
	  * 初始化ViewModel
	  * @author teaphy
	  * @date 2018/7/23 上午10:31
	  */
	abstract fun initViewModel(): S
}