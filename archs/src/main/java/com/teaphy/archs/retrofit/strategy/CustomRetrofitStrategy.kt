package com.teaphy.archs.retrofit.strategy

import com.teaphy.archs.config.ArchConfig
import com.teaphy.archs.retrofit.interceptor.LogCustomInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @desc 策略 - 获取 带有解密拦截器的Retrofit
 * @author teaphy
 * @time 2018/7/28 上午10:45
 */
class CustomRetrofitStrategy : IRetrofitStrategy {


	private object Inner{
		// 创建默认Retrofit
		val INSTANCE =  CustomRetrofitStrategy()
	}

	companion object {
		fun getInstance(): CustomRetrofitStrategy {
			return Inner.INSTANCE
		}
	}

	/**
	  * 通过该方法，获取的是默认Retrofit，且为单例
	  * @author teaphy
	  * @date 2018/7/28 下午2:45
	  */
	override fun getRetrofit(): Retrofit {
		return getInstance().buildDefaultRetrofit()
	}

	private fun buildDefaultRetrofit(): Retrofit {
		val interceptor = LogCustomInterceptor()

		val client = httpClientBuilder {
			connectTimeout(30 * 1000L, TimeUnit.MILLISECONDS)
			readTimeout(30 * 1000L, TimeUnit.MILLISECONDS)
			writeTimeout(30 * 1000L, TimeUnit.MILLISECONDS)
			addInterceptor(interceptor)
		}

		return getRetrofit(client)
	}

	/**
	  * 通过该方法，获取的是默认每次创建新的Retrofit
	  * @author teaphy
	  * @date 2018/7/28 下午2:45
	  */
	override fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {

		return retrofitBuilder {
			baseUrl(ArchConfig.URL_REMOTE_BASE)
			addConverterFactory(GsonConverterFactory.create())
			addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			client(okHttpClient)
		}
	}


	override fun getRetrofit(urlBase: String): Retrofit {
		return retrofitBuilder {
			baseUrl(urlBase)
			addConverterFactory(GsonConverterFactory.create())
			addCallAdapterFactory(RxJava2CallAdapterFactory.create())
		}
	}
}