package ${escapeKotlinIdentifiers(packageName)}

import com.teaphy.archs.base.refresh.BaseRefreshActivity
import android.arch.lifecycle.ViewModelProviders
import com.teaphy.arch.databingding.activity.${activityClass}Binding
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.teaphy.archs.base.refresh.LoadMoreType
import com.teaphy.arch.databingding.adapter.${adapterClass}Binding

class ${activityClass} : BaseRefreshActivity<
		${activityClass}Binding, 
		${viewModel}ViewModel,
		${sourceBean},
		${adapterClass}Binding,
		${adapterClass}Adapter>() {

	// 获取加载的布局
	override fun getLayoutId(): Int {
		return R.layout.${layoutName}
	}

	override fun initData() {
		super.initData()
		// 初始化加载更多的方式
		typeLoadMore = LoadMoreType.ITEMS
	}

	/**
	  * 初始化ViewModel
	  */
	override fun initViewModel(): ${viewModel}ViewModel {
		return ViewModelProviders.of(this).get(${viewModel}ViewModel::class.java)
	}
	
	/**
	 * 订阅ViewModel中的LiveData
	 */
	override fun subscribeObserve() {
		super.subscribeObserve()

	}

	/**
	 * 初始化Adapter
	 */
	override fun produceAdapter(): ${adapterClass}Adapter {
		return ${adapterClass}Adapter()
	}

	/**
	 * 初始化下拉刷新的控件
	 */
	override fun initRefreshLayout(): SmartRefreshLayout {
		return mBinding.smartRefreshLayout
	}

	/**
	 * 初始化RecyclerView
	 */
	override fun initRecyclerView(): RecyclerView {
		return mBinding.recyclerView
	}

	/**
	 * 设置RecyclerView的分割线
	 */
	override fun produceDecoration(): RecyclerView.ItemDecoration? {
		return DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
	}

	/**
	 * 获取数据
	 */
	override fun queryData() {
	}
}
