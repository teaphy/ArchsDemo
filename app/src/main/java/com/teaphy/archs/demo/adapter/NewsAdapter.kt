package com.teaphy.archs.demo.adapter

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.teaphy.archs.adapter.DataBoundListAdapter
import com.teaphy.archs.view.IItemCallback
import com.teaphy.archs.demo.R
import com.teaphy.archs.demo.data.NewsBean
import com.teaphy.archs.demo.databinding.AppItemNewsBinding
import com.teaphy.archs.demo.BR

/**
 * @desc
 * @author teaphy
 * @time 2018/8/23 下午5:02
 */
class NewsAdapter: DataBoundListAdapter<NewsBean, AppItemNewsBinding>(NewsDiffCallback()) {

	var onItemClickCallback: IItemCallback<NewsBean>? = null

	override fun createBinding(parent: ViewGroup): AppItemNewsBinding {
		return DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.app_item_news, parent, false)
	}

	override fun bind(binding: AppItemNewsBinding, item: NewsBean) {
		with(binding) {
			setVariable(BR.newsBean, item)
			setVariable(BR.newsItemClick, onItemClickCallback)
		}
	}
}