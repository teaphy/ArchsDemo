package com.teaphy.archs.demo.adapter

import android.support.v7.util.DiffUtil
import com.teaphy.archs.demo.data.NewsBean

/**
 * @desc
 * @author teaphy
 * @time 2018/8/23 下午5:07
 */
class NewsDiffCallback: DiffUtil.ItemCallback<NewsBean>() {
	override fun areItemsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {

		return oldItem.weburl == newItem.weburl
	}

	override fun areContentsTheSame(oldItem: NewsBean, newItem: NewsBean): Boolean {

		return oldItem.weburl == newItem.weburl
	}
}