package com.teaphy.archs.base

import android.annotation.SuppressLint
import android.databinding.ViewDataBinding
import android.webkit.*

/**
 * @desc WebView 相关Activity基类
 * @author teaphy
 * @time 2018/8/28 下午2:32
 */
abstract class BaseWebViewActivity<P : ViewDataBinding> : BaseActivity<P>() {

	protected lateinit var webView: WebView

	override fun initView() {
		super.initView()

		webView = initWebView()

		configWebView()
	}

	@SuppressLint("SetJavaScriptEnabled")
	private fun configWebView() {
		val webSettings = webView.settings ?: return

		with(webSettings) {
			// 支持 Js 使用
			javaScriptEnabled = true

			// 开启DOM缓存
			domStorageEnabled = true

			// 开启数据库缓存
			databaseEnabled = true

			// 设置 WebView 的缓存模式
			cacheMode = WebSettings.LOAD_NO_CACHE

			// 支持启用缓存模式
			setAppCacheEnabled(true)
			// Android 私有缓存存储，如果你不调用setAppCachePath方法，WebView将不会产生这个目录
			setAppCachePath(cacheDir.absolutePath)

			// 支持缩放
			setSupportZoom(true)

			// 允许加载本地 html 文件/false
			allowFileAccess = true

			// 允许通过 file url 加载的 Javascript 读取其他的本地文件,Android 4.1 之前默认是true，
			// 在 Android 4.1 及以后默认是false,也就是禁止
			allowFileAccessFromFileURLs = false

			// 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源，
			// Android 4.1 之前默认是true，
			// 在 Android 4.1 及以后默认是false,也就是禁止
			// 如果此设置是允许，则 setAllowFileAccessFromFileURLs 不起做用
			allowUniversalAccessFromFileURLs = false
		}

		with(webView) {
			// 初始化WebViewClient
			webViewClient = initWebViewClient()
			// 禁用横向滚动条
			isVerticalScrollBarEnabled = false
			// 禁用竖向滚动条
			isHorizontalScrollBarEnabled = false
		}
	}

	/**
	 * 加载网络链接 url
	 */
	fun loadUrl(url: String) {
		webView.loadUrl(url)
	}

	/**
	 * 加载带有Header的 url
	 */
	fun loadUrl(url: String, conditionHeaders: MutableMap<String, String>) {
		webView.loadUrl(url, conditionHeaders)
	}

	/**
	 * Post方式 加载网络连接url
	 */
	fun postUrl(url: String, postData: ByteArray) {
		webView.postUrl(url, postData)
	}

	/**
	 * 加载Html字符串
	 * 例如： loadData(body, "text/html; charset=UTF-8", null)
	 */
	fun loadData(data: String, mimeType: String, encoding: String) {
		webView.loadData(data, mimeType, encoding)
	}

	/**
	 * 回退到前一页
	 */
	fun goForward() {
		if (webView.canGoForward()) {
			webView.goForward()
		}
	}

	/**
	 * 若 canGoBack为true执行goBack，否则关闭Activity
	 */
	fun goback() {
		if (webView.canGoBack()) {
			webView.goBack()
		} else {
			finish()
		}
	}

	/**
	 * 在调用 onPause()后，可以调用该方法来恢复 WebView 的运行
	 */
	fun wOnResume() {
		webView.onResume()
	}

	/**
	 * 类似 Activity 生命周期，页面进入后台不可见状态
	 */
	fun wOnPause() {
		webView.onPause()
	}

	/**
	 * 清除当前 WebView 访问的历史记录
	 */
	fun wClearHistory() {
		webView.clearHistory()
	}

	/**
	 * 清空网页访问留下的缓存数据。
	 * 需要注意的时，由于缓存是全局的，所以只要是WebView用到的缓存都会被清空，即便其他地方也会使用到。
	 * 该方法接受一个参数，若设为false，则只清空内存里的资源缓存，而不清空磁盘里的。
	 */
	fun wClearCache(includeDiskFiles: Boolean) {
		webView.clearCache(includeDiskFiles)
	}

	/**
	 * 注入 java 对象
	 * 必须要给每个可供js代码调用的Java方法加一个@JavascriptInterface注解
	 */
	@SuppressLint("JavascriptInterface")
	fun wAddJavascriptInterface(any: Any, interfaceName: String) {
		webView.addJavascriptInterface(any, interfaceName)
	}

	// 初始化WebView
	abstract fun initWebView(): WebView

	// 初始化WebViewClient实例
	abstract fun initWebViewClient(): WebViewClient
}