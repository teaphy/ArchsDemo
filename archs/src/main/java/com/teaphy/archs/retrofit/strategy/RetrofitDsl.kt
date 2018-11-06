package com.teaphy.archs.retrofit.strategy

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @desc Retrofit相关的DSL定义
 * @author teaphy
 * @time 2018/7/28 下午2:24
 */

/**
  * 创建OkHttpClient实例
  * @author teaphy
  * @date 2018/7/28 下午2:25
  */
public inline fun httpClientBuilder(builderAction: HttpClientBuilder.() -> Unit): OkHttpClient =
		HttpClientBuilder().apply(builderAction).build()

public inline fun retrofitBuilder(builderAction: Retrofit.Builder.() -> Unit) : Retrofit =
		Retrofit.Builder().apply(builderAction).build()