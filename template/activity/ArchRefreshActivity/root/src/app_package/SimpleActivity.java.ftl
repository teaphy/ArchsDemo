package ${packageName};

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.teaphy.archs.base.refresh.BaseRefreshActivity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.teaphy.arch.databingding.activity.${activityClass}Binding;
import com.teaphy.arch.databingding.adapter.${adapterClass}Binding;
import com.teaphy.archs.base.refresh.LoadMoreType;

public class ${activityClass} extends BaseRefreshActivity<
	${activityClass}Binding, 
	${viewModel}ViewModel,
	${sourceBean},
	${adapterClass}Binding,
	${adapterClass}Adapter> {

	// 获取加载的布局
	@Override
	public int getLayoutId() {
		return R.layout.${layoutName};
	}

	/**
	  * 初始化ViewModel
	  */
	@NonNull
	@Override
	public ${viewModel}ViewModel initViewModel() {
		return ViewModelProviders.of(this).get(${viewModel}ViewModel.class);
	}

	/**
	 * 订阅ViewModel中的LiveData
	 */
	@Override
	public void subscribeObserve() {
		super.subscribeObserve();
	}

	/**
	 * 初始化Adapter
	 */
	@NonNull
	@Override
	public ${adapterClass}Adapter produceAdapter() {
		return new ${adapterClass}Adapter();
	}

	/**
	 * 初始化下拉刷新的控件
	 */
	@NonNull
	@Override
	public SmartRefreshLayout initRefreshLayout() {
		return mBinding.smartRefreshLayout;
	}

	/**
	 * 初始化RecyclerView
	 */
	@NonNull
	@Override
	public RecyclerView initRecyclerView() {
		return mBinding.recyclerView;
	}

	/**
	 * 设置RecyclerView的分割线
	 */
	@Nullable
	@Override
	public RecyclerView.ItemDecoration produceDecoration() {
		return new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
	}

	/**
	 * 获取数据
	 */
	@Override
	public void queryData() {

	}

}