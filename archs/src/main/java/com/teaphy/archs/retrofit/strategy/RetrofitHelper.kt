package com.teaphy.archs.retrofit.strategy

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @desc Retrofit工厂类，根据不同的策略，创建不同的Retrofit实例
 * @author teaphy
 * @time 2018/7/28 下午2:32
 */
class RetrofitHelper(private val retrofitStrategy: IRetrofitStrategy): IRetrofitStrategy {

	override fun getRetrofit(): Retrofit {
		return retrofitStrategy.getRetrofit()
	}

	override fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return retrofitStrategy.getRetrofit(okHttpClient)
	}

	override fun getRetrofit(urlBase: String): Retrofit {
		return retrofitStrategy.getRetrofit(urlBase)
	}

	override fun getRetrofit(urlBase: String, okHttpClient: OkHttpClient): Retrofit {
		return retrofitStrategy.getRetrofit(urlBase, okHttpClient)
	}


}