package com.teaphy.archs.demo.response

import com.teaphy.archs.retrofit.strategy.CustomRetrofitStrategy
import com.teaphy.archs.retrofit.strategy.RetrofitHelper
import com.teaphy.archs.demo.api.NewsApi
import com.teaphy.archs.demo.data.JuheResult
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @desc
 * @author teaphy
 * @time 2018/8/30 上午11:07
 */
class NewsResponse  {

	val customApi = RetrofitHelper(CustomRetrofitStrategy()).getRetrofit()
	.create(NewsApi::class.java)

	fun queryNews(params: MutableMap<String, String>): Flowable<JuheResult> {
		return customApi.queryNews(params)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
	}
}