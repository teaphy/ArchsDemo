package com.teaphy.archs.demo.response

import com.teaphy.archs.base.BaseResponse
import com.teaphy.archs.base.BaseResponseBean
import com.teaphy.archs.retrofit.strategy.CustomRetrofitStrategy
import com.teaphy.archs.retrofit.strategy.RetrofitHelper
import com.teaphy.archs.demo.api.JdNewsApi
import com.teaphy.archs.demo.data.JdNewsResult
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @desc
 * @author teaphy
 * @time 2018/8/30 上午11:07
 */
class NewsReponse  {

	val customApi = RetrofitHelper(CustomRetrofitStrategy()).getRetrofit()
	.create(JdNewsApi::class.java)

	fun queryNews(parmas: MutableMap<String, Any>): Flowable<JdNewsResult> {
		return customApi.queryJdNews(parmas)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
	}
}