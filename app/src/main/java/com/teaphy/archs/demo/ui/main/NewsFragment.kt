package com.teaphy.archs.demo.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.teaphy.archs.base.BaseWebViewFragment

import com.teaphy.archs.demo.R
import com.teaphy.archs.demo.databinding.FragmentNewsBinding

class NewsFragment : BaseWebViewFragment<FragmentNewsBinding>() {
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
		return R.layout.fragment_news
	}


	override fun initView() {
		super.initView()
		val url = "https://tech.sina.com.cn/i/2018-08-28/doc-ihiixyeu0530459.shtml"
		loadUrl(url)
	}

}
