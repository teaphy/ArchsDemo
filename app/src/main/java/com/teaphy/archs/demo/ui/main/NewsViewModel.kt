package com.teaphy.archs.demo.ui.main

import android.arch.lifecycle.MutableLiveData
import com.teaphy.archs.base.BaseViewModel
import com.teaphy.archs.demo.data.NewsBean
import com.teaphy.archs.demo.global.ArchsDemoConstant
import com.teaphy.archs.demo.response.NewsResponse

/**
 * @desc
 * @author teaphy
 * @time 2018/8/29 下午3:03
 */
class NewsViewModel : BaseViewModel() {

    private val newsResponse = NewsResponse()

    val newsLiveData = MutableLiveData<List<NewsBean>>()

    fun queryNews() {
        val ds = newsResponse.queryNews(produceJdNewsParam())
                .subscribe({ it ->
                    if (it.error_code == 0
                            && it.result.data != null
                            && it.result.data.isNotEmpty()) {
                        newsLiveData.postValue(it.result.data)
                    } else {
                        newsLiveData.postValue(null)
                    }

                }, {
                    newsLiveData.postValue(null)
                })
        addDisposable(ds)
    }

    private fun produceJdNewsParam(): MutableMap<String, String> {
        return mutableMapOf(Pair("type", "top"), Pair("key", ArchsDemoConstant.KEY_JUHE_NEWS))
    }

}