package ${escapeKotlinIdentifiers(packageName)}

import android.arch.lifecycle.ViewModelProviders
import com.teaphy.arch.databingding.fragment.${className}Binding
import com.teaphy.archs.base.BaseVmFragment

class ${className} : BaseVmFragment<${className}Binding, ${viewModel}ViewModel>() {

	override fun getLayoutId(): Int {
		return R.layout.${fragmentName}
	}
	
	override fun initViewModel(): ${viewModel}ViewModel {
		return ViewModelProviders.of(this).get(${viewModel}ViewModel::class.java)
	}

	override fun showLoading() {
	}

	override fun hideLoading() {
	}
}
