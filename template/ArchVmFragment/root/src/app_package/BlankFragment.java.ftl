package ${packageName};

import android.support.annotation.NonNull;

import android.arch.lifecycle.ViewModelProviders;

import com.teaphy.arch.databingding.fragment.${className}Binding;
import com.teaphy.archs.base.BaseVmFragment;

public class ${className} extends BaseVmFragment<${className}Binding, ${viewModel}ViewModel> {

	//  获取加载的布局
	@Override
	public int getLayoutId() {
		return R.layout.${fragmentName};
	}

	@NonNull
	@Override
	public ${viewModel}ViewModel initViewModel() {
		return ViewModelProviders.of(this).get(${viewModel}ViewModel.class);
	}

	@Override
	public void showLoading() {
	}

	@Override
	public void hideLoading() {
	}
}