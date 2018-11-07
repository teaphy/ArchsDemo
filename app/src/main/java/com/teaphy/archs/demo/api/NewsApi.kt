package com.teaphy.archs.demo.api

import com.teaphy.archs.demo.data.JuheResult
import com.teaphy.archs.demo.global.UrlConstant
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @desc
 * @author teaphy
 * @time 2018/8/23 下午4:15
 */
interface NewsApi {

	@GET(UrlConstant.QUERY_NEWS)
	fun queryNews(@QueryMap param: MutableMap<String, String>): Flowable<JuheResult>
}