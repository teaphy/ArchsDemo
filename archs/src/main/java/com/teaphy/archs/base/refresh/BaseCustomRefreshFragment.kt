package com.teaphy.archs.base.refresh

import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.teaphy.archs.adapter.DataBoundListAdapter
import com.teaphy.archs.base.BaseViewModel
import com.teaphy.archs.base.BaseVmFragment
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener

/**
 * @desc 上拉加载/下拉刷新的 Fragment 基类
 * @author teaphy
 * @time 2018/7/27 下午3:02
 * P 用来指定当前Fragment默认的ViewDataBinding
 * S 用来指定ViewModel的类型
 * T 用来指定item的数据类型
 * K 用来的指定Adapter的ViewDataBinding的类型
 * A 用来指定Adapter的数据类型
 */
abstract class BaseCustomRefreshFragment<
		P : ViewDataBinding,
		S : BaseViewModel,
		T,
		K : ViewDataBinding,
		A : ListAdapter<T, RecyclerView.ViewHolder>> : BaseVmFragment<P, S>() {

	lateinit var refreshLayout: SmartRefreshLayout
	lateinit var recyclerView: RecyclerView

	val mList = mutableListOf<T>()
	lateinit var mAdapter: A

	// 总页数
	protected var totalPage: Int = 0
	// 当前页数
	protected var currentPage: Int = 1
	// 总条数
	protected var totalItem: Int = 0
	// 默认以Item条数来判断是否可以加载更多
	protected var typeLoadMore = LoadMoreType.ITEMS
	// 判断当前加载数据的方式：true - 刷新数据，
	protected var isRefresh: Boolean = false


	override fun initData() {
		super.initData()
		mAdapter = produceAdapter()
	}

	override fun initView() {
		super.initView()

		refreshLayout = initRefreshLayout()

		recyclerView = initRecyclerView()

		with(recyclerView) {
			layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
			adapter = mAdapter
			addItemDecoration(produceDecoration()!!)
		}

		// 设置可以自动加载更多
		refreshLayout.setEnableAutoLoadMore(true)
	}

	override fun setListener() {
		super.setListener()

		with(refreshLayout) {

			setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
				override fun onLoadMore(refreshLayout: RefreshLayout) {
					loadingData(false)
				}

				override fun onRefresh(refreshLayout: RefreshLayout) {
					loadingData(true)
				}

			})
		}
	}

	/**
	 * 加载数据
	 * @param isRefresh 是否为刷新数据
	 */
	protected fun loadingData(isRefresh: Boolean) {
		this.isRefresh = isRefresh
		currentPage = if (isRefresh) 1 else ++currentPage
		queryData()
	}

	/**
	 * 刷新数据
	 */
	protected fun refreshData(dataSource: RefreshDataSource<T>) {
		if (dataSource.isSuccess
				&& null != dataSource.data) {
			totalItem = dataSource.totalItem
			totalPage = dataSource.totalPage
			fillingData(dataSource.data)
			enableRefreshOrMore(true)
		} else {
			enableRefreshOrMore(false)
		}
	}

	/**
	 * 装填数据
	 */
	private fun fillingData(listData: List<T>) {

		// 更新UI
		val list = mutableListOf<T>()
		if (isRefresh) {
			mList.clear()
		}

		mList.addAll(listData)

		list.addAll(mList)

		mAdapter.submitList(list)

		enableRefreshOrMore(true)
	}

	/**
	 * 配置是否可以加载更多
	 */
	protected fun enableRefreshOrMore(isSuccess: Boolean) {
		with(refreshLayout) {
			// 设置加载数据是成功还是失败
			if (isRefresh) {
				finishRefresh(isSuccess)
			} else {
				finishLoadMore(isSuccess)
			}

			// 加载成功,设置是否加载更多
			if (isSuccess) {
				when (typeLoadMore) {
					LoadMoreType.ITEMS -> setEnableLoadMore(mList.size < totalItem)
					LoadMoreType.PAGE -> setEnableLoadMore(currentPage < totalPage)
				}
			}
		}
	}


	/**
	 * 初始化Adapter
	 */
	abstract fun produceAdapter(): A

	/**
	 * 初始化SmartRefreshLayout
	 */
	abstract fun initRefreshLayout(): SmartRefreshLayout

	/**
	 * RecyclerView
	 */
	abstract fun initRecyclerView(): RecyclerView

	/**
	 * 初始化 RecyclerView的Item之间的分割线
	 */
	abstract fun produceDecoration(): RecyclerView.ItemDecoration?

	/**
	 * 获取数据
	 */
	abstract fun queryData()
}