package com.teaphy.archs.demo.ui.main


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import com.teaphy.archs.base.refresh.BaseRefreshFragment
import com.teaphy.archs.base.refresh.RefreshDataSource
import com.teaphy.archs.view.IItemCallback

import com.teaphy.archs.demo.R
import com.teaphy.archs.demo.adapter.NewsAdapter
import com.teaphy.archs.demo.databinding.AppItemNewsBinding
import com.teaphy.archs.demo.databinding.FragmentHomeBinding
import com.teaphy.archs.demo.ui.news.NewsWebViewActivity
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.teaphy.archs.demo.data.NewsBean

/**
 * 首页
 * P 用来指定当前Fragment默认的ViewDataBinding
 * S 用来指定ViewModel的类型
 * T 用来指定item的数据类型
 * K 用来的指定Adapter的ViewDataBinding的类型
 * A 用来指定Adapter的数据类型
 */
class HomeFragment : BaseRefreshFragment<FragmentHomeBinding, NewsViewModel, NewsBean, AppItemNewsBinding, NewsAdapter>() {

	val channel: String = "头条"
	val appkey: String = "9b0a1fc306c03c2bc7659e9073fe7758"
	var num: Int = 10
	var start: Int = 0

	override fun onStart() {
		super.onStart()

		loadingData(true)
	}

	override fun setListener() {
		super.setListener()

		mAdapter.onItemClickCallback = object : IItemCallback<NewsBean> {
			override fun onItemClick(item: NewsBean) {
				NewsWebViewActivity.gotoNewsWebViewActivity(context!!, item.url, item.title)
			}

		}
	}

	override fun produceAdapter(): NewsAdapter {
		return NewsAdapter()
	}

	override fun initRefreshLayout(): SmartRefreshLayout {
		return mBinding.srlNews
	}

	override fun initRecyclerView(): RecyclerView {
		return mBinding.rvNews
	}

	override fun produceDecoration(): RecyclerView.ItemDecoration? {
		return DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL)
	}

	override fun queryData() {
		mViewModel.queryNews()
	}

	override fun initViewModel(): NewsViewModel {
		return ViewModelProviders.of(this).get(NewsViewModel::class.java)
	}

	override fun getLayoutId(): Int {
		return R.layout.fragment_home
	}

	override fun showLoading() {

	}

	override fun hideLoading() {
	}

	override fun subscribeObserve() {
		super.subscribeObserve()

		mViewModel.newsLiveData.observe(this, Observer {
			if (null == it) {
				refreshData(RefreshDataSource(false))
			} else {
				refreshData(RefreshDataSource(true, data = it, totalItem = 35))
			}
		})
	}
}
