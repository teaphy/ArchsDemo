package ${packageName};

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public class ${adapterClass}Diff extends DiffUtil.ItemCallback<${sourceBean}> {
  
	/**
	 * 验证是否为同一个Item
	 */
	@Override
	public boolean areItemsTheSame(@NonNull ${sourceBean} oldItem, @NonNull ${sourceBean} newItem) {
		return false;
	}

	/**
	 * 验证新旧item的内容是否修改
	 */
	@Override
	public boolean areContentsTheSame(@NonNull ${sourceBean} oldItem, @NonNull ${sourceBean} newItem) {
		return false;
	}
}