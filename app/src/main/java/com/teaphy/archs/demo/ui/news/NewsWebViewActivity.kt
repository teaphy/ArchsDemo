package com.teaphy.archs.demo.ui.news

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.webkit.*
import com.teaphy.archs.base.BaseWebViewActivity
import com.teaphy.archs.demo.R
import com.teaphy.archs.demo.databinding.ActivityNewsWebViewBinding


class NewsWebViewActivity : BaseWebViewActivity<ActivityNewsWebViewBinding>() {

	override fun initWebView(): WebView {
		return mBinding.wvNews
	}

	override fun initWebViewClient(): WebViewClient {
		return object : WebViewClient() {

			/**
			 * 是否在 WebView 内加载页面
			 */
			override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
				view.loadUrl(url)
				return true
			}
		}
	}

	override fun getLayoutId(): Int {
		return R.layout.activity_news_web_view
	}

	lateinit var mUrl: String
	lateinit var mTitle: String

	companion object {

		fun gotoNewsWebViewActivity(context: Context, url: String, title: String) {
			val intent = Intent(context, NewsWebViewActivity::class.java)
			val bundle = Bundle()

			bundle.putString("url", url)
			bundle.putString("title", title)

			intent.putExtras(bundle)
			context.startActivity(intent)
		}
	}

	override fun initData() {
		super.initData()
		val bundle = intent.extras
		if (null != bundle) {
			with(bundle) {
				mUrl = getString("url")
				mTitle = getString("title")
			}
		}
	}

	override fun initView() {
		super.initView()
		mBinding.tvTitle.text = mTitle

		loadUrl(mUrl)
	}

	override fun setListener() {
		super.setListener()
		mBinding.iftvBack.setOnClickListener {
			finish()
		}
	}

//	override fun onCreate(savedInstanceState: Bundle?) {
//		super.onCreate(savedInstanceState)
//		setContentView(R.layout.activity_news_web_view)
//
//		wvNews = findViewById(R.id.wvNews)
//		tvBack = findViewById(R.id.iftvBack)
//		tvTitle = findViewById(R.id.tvTitle)
//
//		initData()
//
//		initView()
//
//		setListener()
//	}
//
//	private fun initData() {
//		val bundle = intent.extras
//		if (null != bundle) {
//			with(bundle) {
//				mUrl = getString("url")
//				mTitle = getString("title")
//			}
//		}
//	}
//
//	private fun initView() {
//		tvTitle.text = mTitle
//
//		initWebView()
//
//	}
//
//	private fun initWebView() {
//		val webSettings = wvNews.settings ?: return
//
//		with(webSettings) {
//			// 支持 Js 使用
//			setJavaScriptEnabled(true)
//
//			// 开启DOM缓存
//			domStorageEnabled = true
//
//			// 开启数据库缓存
//			databaseEnabled = true
//
//			// 设置 WebView 的缓存模式
//			cacheMode = WebSettings.LOAD_NO_CACHE
//
//			// 支持启用缓存模式
//			setAppCacheEnabled(true)
//			// Android 私有缓存存储，如果你不调用setAppCachePath方法，WebView将不会产生这个目录
//			setAppCachePath(cacheDir.absolutePath)
//
//			// 支持缩放
//			setSupportZoom(true)
//
//			// 允许加载本地 html 文件/false
//			allowFileAccess = true
//
//			// 允许通过 file url 加载的 Javascript 读取其他的本地文件,Android 4.1 之前默认是true，
//			// 在 Android 4.1 及以后默认是false,也就是禁止
//			allowFileAccessFromFileURLs = false
//
//			// 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源，
//			// Android 4.1 之前默认是true，
//			// 在 Android 4.1 及以后默认是false,也就是禁止
//			// 如果此设置是允许，则 setAllowFileAccessFromFileURLs 不起做用
//			allowUniversalAccessFromFileURLs = false
//
//		}
//
//		wvNews.webViewClient = object : WebViewClient(){
//			/**
//			 * 当WebView得页面Scale值发生改变时回调
//			 */
//			override fun onScaleChanged(view: WebView, oldScale: Float, newScale: Float) {
//				super.onScaleChanged(view, oldScale, newScale)
//			}
//
//			/**
//			 * 是否在 WebView 内加载页面
//			 *
//			 * @param view
//			 * @param url
//			 * @return
//			 */
//			override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//				view.loadUrl(url)
//				return true
//			}
//
//			/**
//			 * WebView 开始加载页面时回调，一次Frame加载对应一次回调
//			 *
//			 * @param view
//			 * @param url
//			 * @param favicon
//			 */
//			override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
//				super.onPageStarted(view, url, favicon)
//			}
//
//			/**
//			 * WebView 完成加载页面时回调，一次Frame加载对应一次回调
//			 *
//			 * @param view
//			 * @param url
//			 */
//			override fun onPageFinished(view: WebView, url: String) {
//				super.onPageFinished(view, url)
//			}
//
//			/**
//			 * WebView 加载页面资源时会回调，每一个资源产生的一次网络加载，除非本地有当前 url 对应有缓存，否则就会加载。
//			 *
//			 * @param view WebView
//			 * @param url  url
//			 */
//			override fun onLoadResource(view: WebView, url: String) {
//				super.onLoadResource(view, url)
//			}
//
//			/**
//			 * WebView 可以拦截某一次的 request 来返回我们自己加载的数据，这个方法在后面缓存会有很大作用。
//			 *
//			 * @param view    WebView
//			 * @param request 当前产生 request 请求
//			 * @return WebResourceResponse
//			 */
//			override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
//				return super.shouldInterceptRequest(view, request)
//			}
//
//			/**
//			 * WebView 访问 url 出错
//			 *
//			 * @param view
//			 * @param request
//			 * @param error
//			 */
//			override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
//				super.onReceivedError(view, request, error)
//			}
//
//			/**
//			 * WebView ssl 访问证书出错，handler.cancel()取消加载，handler.proceed()对然错误也继续加载
//			 *
//			 * @param view
//			 * @param handler
//			 * @param error
//			 */
//			override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
//				super.onReceivedSslError(view, handler, error)
//			}
//		}
//
//		loadUrl()
//	}
//
//	fun loadUrl() {
//		wvNews.loadUrl(mUrl)
//	}
//
//	private fun setListener() {
//
//		tvBack.setOnClickListener {
//			finish()
//		}
//	}
}
