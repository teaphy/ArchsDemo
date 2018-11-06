package ${escapeKotlinIdentifiers(packageName)}

import com.teaphy.archs.base.BaseVmActivity
import android.arch.lifecycle.ViewModelProviders
import com.teaphy.arch.databingding.activity.${activityClass}Binding

class ${activityClass} : BaseVmActivity<${activityClass}Binding, ${viewModel}ViewModel>() {

	// 获取加载的布局
	override fun getLayoutId(): Int {
		return R.layout.${layoutName}
	}

	override fun initViewModel(): ${viewModel}ViewModel {
		return ViewModelProviders.of(this).get(${viewModel}ViewModel::class.java)
	}
}
