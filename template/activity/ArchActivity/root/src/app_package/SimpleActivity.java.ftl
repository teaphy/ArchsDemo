package ${packageName};


import com.teaphy.archs.base.BaseActivity;
import com.teaphy.arch.databingding.activity.${activityClass}Binding;

public class ${activityClass} extends BaseActivity<${activityClass}Binding> {

    //  获取加载的布局
	@Override
	public int getLayoutId() {
		return R.layout.${layoutName};
	}
}