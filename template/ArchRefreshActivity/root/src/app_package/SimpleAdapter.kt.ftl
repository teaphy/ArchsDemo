package ${escapeKotlinIdentifiers(packageName)}

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teaphy.archs.adapter.DataBoundListAdapter
import com.teaphy.arch.databingding.adapter.${adapterClass}Binding

class ${adapterClass}Adapter : DataBoundListAdapter<${sourceBean}, ${adapterClass}Binding>(${adapterClass}Diff()) {


	override fun createBinding(parent: ViewGroup): ${adapterClass}Binding {
		return DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_${adapterLayoutName}, parent, false)
	}

	override fun bind(binding: ${adapterClass}Binding, item: ${sourceBean}) {
		with(binding) {

		}
	}

	
}