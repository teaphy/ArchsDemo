package ${escapeKotlinIdentifiers(packageName)}

import android.support.v7.util.DiffUtil

class ${adapterClass}Diff: DiffUtil.ItemCallback<${sourceBean}>() {

    /**
      * 验证是否为同一个Item
      */
    override fun areItemsTheSame(oldItem: ${sourceBean}, newItem: ${sourceBean}): Boolean {

        return true
    }

    /**
      * 验证新旧item的内容是否修改
      */
    override fun areContentsTheSame(oldItem: ${sourceBean}, newItem: ${sourceBean}): Boolean {

        return true
    }
}