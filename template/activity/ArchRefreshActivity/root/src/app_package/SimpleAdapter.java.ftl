package ${packageName};

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.teaphy.archs.adapter.DataBoundListAdapter;
import com.teaphy.arch.databingding.adapter.${adapterClass}Binding;

public class ${adapterClass}Adapter extends DataBoundListAdapter<${sourceBean}, ${adapterClass}Binding> {

	public ${adapterClass}Adapter() {
		super(new ${adapterClass}Diff());
	}

	@NonNull
	@Override
	protected ${adapterClass}Binding createBinding(@NonNull ViewGroup parent) {
		return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_${adapterLayoutName}, parent, false);
	}

	@Override
	protected void bind(@NonNull ${adapterClass}Binding binding, ${sourceBean} item) {

	}
}