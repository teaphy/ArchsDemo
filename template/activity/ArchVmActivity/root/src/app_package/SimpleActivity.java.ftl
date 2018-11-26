package ${packageName};

import android.arch.lifecycle.ViewModelProviders;
import com.teaphy.arch.databingding.activity.${activityClass}Binding;
import com.teaphy.archs.base.BaseVmActivity;
import android.support.annotation.NonNull;

public class ${activityClass} extends BaseVmActivity<${activityClass}Binding, ${viewModel}ViewModel>{

	//  获取加载的布局
	@Override
	public int getLayoutId() {
		return R.layout.${layoutName};
	}

	@NonNull
	@Override
	public ${viewModel}ViewModel initViewModel() {
		return ViewModelProviders.of(this).get(${viewModel}ViewModel.class);
	}
}