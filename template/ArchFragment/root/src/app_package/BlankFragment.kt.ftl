package ${escapeKotlinIdentifiers(packageName)}


import com.teaphy.arch.databingding.fragment.${className}Binding
import com.teaphy.archs.base.BaseFragment

class ${className} : BaseFragment<${className}Binding>() {

	override fun getLayoutId(): Int {
		return R.layout.${fragmentName}
	}

}
