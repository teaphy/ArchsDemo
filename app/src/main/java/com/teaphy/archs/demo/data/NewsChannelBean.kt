package com.teaphy.archs.demo.data

/**
 * @desc
 * @author teaphy
 * @time 2018/8/23 下午3:49
 */
class NewsChannelBean(var channel: String = "", var num: Int = 1, val list: List<NewsBean>? = mutableListOf() ) {

	override fun toString(): String {
		return "NewsChannelBean(channel='$channel', num=$num, list=$list)"
	}
}