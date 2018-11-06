package com.teaphy.archs.retrofit.others


import com.teaphy.archs.retrofit.interceptor.LogCustomInterceptor
import com.teaphy.archs.retrofit.request.ProgressResponseBody
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 普通网络访问
 * Created by teaphy
 * on 2016/6/24.
 */
class DownloadFileRetrofit private constructor() {

	private object Inner{
		val INSTANCE = DownloadFileRetrofit()
	}

	companion object {
		fun getInstance(): DownloadFileRetrofit {
			return Inner.INSTANCE
		}
	}

	fun getRetrofit(baseUrl: String, responseListener: ProgressResponseBody.ProgressResponseListener): Retrofit {
		val interceptor = LogCustomInterceptor()
		val client = OkHttpClient.Builder()
				.connectTimeout(30 * 1000L, TimeUnit.MILLISECONDS)
				.readTimeout(30 * 1000L, TimeUnit.MILLISECONDS)
				.writeTimeout(30 * 1000L, TimeUnit.MILLISECONDS)
				.addInterceptor(interceptor)
				.build()

		return Retrofit.Builder()
				.baseUrl(baseUrl)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build()
	}
}
