package com.teaphy.archs.demo.ui.main

import android.arch.lifecycle.MutableLiveData
import com.teaphy.archs.base.BaseViewModel
import com.teaphy.archs.retrofit.strategy.CustomRetrofitStrategy
import com.teaphy.archs.retrofit.strategy.RetrofitHelper
import com.teaphy.archs.demo.api.JdNewsApi
import com.teaphy.archs.demo.data.NewsBean
import com.teaphy.archs.demo.response.NewsReponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @desc
 * @author teaphy
 * @time 2018/8/29 下午3:03
 */
class NewsViewModel : BaseViewModel() {

	private val newsResponse = NewsReponse()

	val newsLiveData = MutableLiveData<List<NewsBean>>()

	fun queryNews(channel: String, num: Int, currentPage: Int, appkey: String) {
		val ds = newsResponse.queryNews(produceJdNewsParam(channel, num, currentPage, appkey))
				.subscribe({ it ->
					if (it?.result != null
							&& null != it.result?.result
							&& null != it.result?.result?.list
							&& it.result!!.result.list!!.isNotEmpty()) {
						newsLiveData.postValue(it.result!!.result.list!!)
					} else {
						newsLiveData.postValue(null)
					}

				}, {
					newsLiveData.postValue(null)
				})
		addDisposable(ds)
	}

	private fun produceJdNewsParam(channel: String, num: Int, currentPage: Int, appkey: String): MutableMap<String, Any> {
		return mutableMapOf(Pair("channel", channel), Pair("num", num), Pair("start", currentPage * num), Pair("appkey", appkey))
	}

}