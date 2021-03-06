package com.teaphy.archs.base

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.teaphy.archs.R
import com.teaphy.archs.utils.ImmeriveUtils
import com.teaphy.archs.view.IBackView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.annotations.NotNull
import java.lang.Exception

/**
  * Activity基类
  * @author teaphy
  * @date 2018/7/27 下午2:24  
  */
abstract class BaseActivity<P : ViewDataBinding>: AppCompatActivity(), IBackView {

	lateinit var mBinding: P

	private val mCompositeDisposable: CompositeDisposable by lazy {
		CompositeDisposable()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		mBinding = createDataBinding()

		initData()

		initView()

		setListener()

		loadRemoteSource()
	}

	private fun createDataBinding(): P {
		return DataBindingUtil.setContentView(this, getLayoutId()) as P
	}

	override fun onDestroy() {
		super.onDestroy()

		if (!mCompositeDisposable.isDisposed) {
			mCompositeDisposable.dispose()
		}
	}

	override fun killSelf() {
		finish()
	}

	override fun finish() {
		finishBefore()
		super.finish()
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
	 * 加载远程数据
	 */
	open fun loadRemoteSource() {
	
	}

	/**
	  *  finish之前 调用 用于设置 setResult
	  * @autor teaphy
	  * @date 2018/5/14 下午1:54
	  */
	open fun finishBefore() {

	}

	/**
	  * 统一管理订阅
	  * @author teaphy
	  * @date 2018/7/27 下午2:17
	  */
	fun addDisposable(disposable: Disposable) {
		mCompositeDisposable.add(disposable)
	}

	fun addDisposable(operate: () -> Disposable) {
		mCompositeDisposable.add(operate.invoke())
	}

	/**
	 * 设置标题
	 */
	protected fun setTitle(id: Int, title: String) {
		try {
			val titleText = findViewById<TextView>(id)
			titleText.text = title
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	protected fun setTitle(id: Int,@StringRes titleRes: Int) {
		try {
			val titleText = findViewById<TextView>(id)
			titleText.setText(titleRes)
		} catch (e: Exception) {
			e.printStackTrace()
		}
	}

	/**
	 * 设置返回事件
	 */
	protected fun registerBack(id: Int) {
		val backView: View = findViewById(id)
		backView.setOnClickListener {
			finish()
		}
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
	fun launchActivity(intent: Intent) {
		startActivity(intent)
	}

	fun launchActivityForResult(intent: Intent, requestCode: Int) {
		startActivityForResult(intent, requestCode)
	}

	fun launchActivity(clazz: Class<*>) {
		val intent = Intent(this, clazz)
		launchActivity(intent)
	}

	fun launchActivity(clazz: Class<*>, bundle: Bundle) {
		val intent = Intent(this, clazz)
		intent.putExtras(bundle)
		launchActivity(intent)
	}

	fun launchActivityForResult(clazz: Class<*>, requestCode: Int) {
		val intent = Intent(this, clazz)
		launchActivityForResult(intent, requestCode)
	}

	fun launchActivityForResult(clazz: Class<*>, bundle: Bundle, requestCode: Int) {
		val intent = Intent(this, clazz)
		intent.putExtras(bundle)
		launchActivityForResult(intent, requestCode)
	}

	/**
	 * 具体沉浸的样式，可以根据需要自行修改状态栏和导航栏的颜色
	 */
	fun immersive() {
		ImmeriveUtils.immersive(this, android.R.color.transparent, android.R.color.transparent)
	}
}