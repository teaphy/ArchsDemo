package com.teaphy.archs.base

import android.annotation.SuppressLint
import android.databinding.ViewDataBinding
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.util.DisplayMetrics



/**
 * @desc
 * @author teaphy
 * @time 2018/8/28 下午3:30
 */
abstract class BaseWebViewFragment<P : ViewDataBinding> : BaseFragment<P>() {

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
			setAppCachePath(context!!.cacheDir.absolutePath)

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

			//设置加载进来的页面自适应手机屏幕
//			useWideViewPort = true
//			loadWithOverviewMode = true
			// 通过百分比来设置文字的大小，默认值是100。
			textZoom = 100
			
			moreWebSettings(this)
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
	fun loadData(data: String, mimeType: String, encoding: String?) {
		webView.loadData(data, mimeType, encoding)
	}

	fun loadData(data: String) {
		loadData(data, "text/html; charset=UTF-8", null)
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
	
	/**
	 * WebView - WebSettings
	 */
	open fun moreWebSettings(settings: WebSettings) {
	
	}

	// 初始化WebView
	abstract fun initWebView(): WebView

	// 初始化WebViewClient实例
	abstract fun initWebViewClient(): WebViewClient

//	object : WebViewClient(){
//		/**
//		 * 当WebView得页面Scale值发生改变时回调
//		 */
//		override fun onScaleChanged(view: WebView, oldScale: Float, newScale: Float) {
//			super.onScaleChanged(view, oldScale, newScale)
//		}
//
//		/**
//		 * 是否在 WebView 内加载页面
//		 *
//		 * @param view
//		 * @param url
//		 * @return
//		 */
//		override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//			view.loadUrl(url)
//			return true
//		}
//
//		/**
//		 * WebView 开始加载页面时回调，一次Frame加载对应一次回调
//		 *
//		 * @param view
//		 * @param url
//		 * @param favicon
//		 */
//		override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
//			super.onPageStarted(view, url, favicon)
//		}
//
//		/**
//		 * WebView 完成加载页面时回调，一次Frame加载对应一次回调
//		 *
//		 * @param view
//		 * @param url
//		 */
//		override fun onPageFinished(view: WebView, url: String) {
//			super.onPageFinished(view, url)
//		}
//
//		/**
//		 * WebView 加载页面资源时会回调，每一个资源产生的一次网络加载，除非本地有当前 url 对应有缓存，否则就会加载。
//		 *
//		 * @param view WebView
//		 * @param url  url
//		 */
//		override fun onLoadResource(view: WebView, url: String) {
//			super.onLoadResource(view, url)
//		}
//
//		/**
//		 * WebView 可以拦截某一次的 request 来返回我们自己加载的数据，这个方法在后面缓存会有很大作用。
//		 *
//		 * @param view    WebView
//		 * @param request 当前产生 request 请求
//		 * @return WebResourceResponse
//		 */
//		override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
//			return super.shouldInterceptRequest(view, request)
//		}
//
//		/**
//		 * WebView 访问 url 出错
//		 *
//		 * @param view
//		 * @param request
//		 * @param error
//		 */
//		override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
//			super.onReceivedError(view, request, error)
//		}
//
//		/**
//		 * WebView ssl 访问证书出错，handler.cancel()取消加载，handler.proceed()对然错误也继续加载
//		 *
//		 * @param view
//		 * @param handler
//		 * @param error
//		 */
//		override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
//			super.onReceivedSslError(view, handler, error)
//		}
//	}
}