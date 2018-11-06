package ${escapeKotlinIdentifiers(packageName)}

import com.teaphy.archs.base.BaseActivity
import com.teaphy.arch.databingding.activity.${activityClass}Binding


class ${activityClass} : BaseActivity<${activityClass}Binding>() {

	// 获取加载的布局
	override fun getLayoutId(): Int {
		return R.layout.${layoutName}
	}
}
