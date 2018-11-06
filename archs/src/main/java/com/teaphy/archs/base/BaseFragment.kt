package com.teaphy.archs.base

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.annotations.NotNull

abstract class BaseFragment<P: ViewDataBinding> : Fragment() {

	lateinit var mBinding: P
	private val mCompositeDisposable: CompositeDisposable by lazy {
		CompositeDisposable()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initData()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

		mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container,  false)

		initView()

		setListener()

		return mBinding.root
	}

	override fun onDestroy() {
		super.onDestroy()

		if (!mCompositeDisposable.isDisposed) {
			mCompositeDisposable.dispose()
		}
	}


	/**
	 * 获取加载的布局
	 * @author teaphy
	 * @date 2018/7/27 下午2:35
	 */
	abstract fun getLayoutId(): Int

	/**
	 * 初始化数据
	 * @author teaphy
	 * @date 2018/7/27 下午2:22
	 */
	open fun initData() {

	}

	/**
	 * 初始化View
	 * @author teaphy
	 * @date 2018/7/27 下午2:18
	 */
	open fun initView() {

	}

	/**
	 * 设置监听
	 * @author teaphy
	 * @date 2018/7/27 下午2:18
	 */
	open fun setListener() {

	}

	/**
	 * 统一管理订阅
	 * @author teaphy
	 * @date 2018/7/27 下午2:17
	 */
	fun addDisposable(disposable: Disposable) {
		mCompositeDisposable.add(disposable)
	}

	/**
	 * Toast
	 * @author teaphy
	 * @date 2018/7/27 下午2:17
	 */
	fun showMessage(message: String) {
		ToastUtils.showShort(message)
	}

	/**
	 * Toast
	 * @author teaphy
	 * @date 2018/7/27 下午2:17
	 */
	fun showMessage(@StringRes messageRes: Int) {
		ToastUtils.showShort(messageRes)
	}

	/**
	 * 设置监听
	 * @author teaphy
	 * @date 2018/7/27 下午2:48
	 */
	fun <T : ViewDataBinding>setVariable(binding: T, variableId: Int,@NotNull value: Any) {
		with(binding){
			setVariable(variableId, value)
			executePendingBindings()
		}
	}

	/**
	 * 打开Activity
	 */
	fun openActivity(intent: Intent) {
		startActivity(intent)
	}

	fun openActivityForResult(intent: Intent, requestCode: Int) {
		startActivityForResult(intent, requestCode)
	}

	fun openActivity(clazz: Class<*>) {
		val intent = Intent(activity, clazz)
		openActivity(intent)
	}

	fun openActivity(clazz: Class<*>, bundle: Bundle) {
		val intent = Intent(activity, clazz)
		intent.putExtras(bundle)
		openActivity(intent)
	}

	fun openActivityForResult(clazz: Class<*>, requestCode: Int) {
		val intent = Intent(activity, clazz)
		openActivityForResult(intent, requestCode)
	}

	fun openActivityForResult(clazz: Class<*>, bundle: Bundle, requestCode: Int) {
		val intent = Intent(activity, clazz)
		intent.putExtras(bundle)
		openActivityForResult(intent, requestCode)
	}
}