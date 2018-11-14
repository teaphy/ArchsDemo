package com.teaphy.archs.retrofit.strategy

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @desc 获取Retrofit策略的接口
 * @author teaphy
 * @time 2018/7/28 上午10:42
 */
interface IRetrofitStrategy {
	// 采用默认OkHttpClient
	fun getRetrofit(): Retrofit

	// 自定义OkHttpClient
	fun getRetrofit(okHttpClient: OkHttpClient): Retrofit

	// 自定义BaseURL
	fun getRetrofit(urlBase: String): Retrofit
	
	// 自定义BaseURL及OkHttpClient
	fun getRetrofit(urlBase: String, okHttpClient: OkHttpClient): Retrofit
}