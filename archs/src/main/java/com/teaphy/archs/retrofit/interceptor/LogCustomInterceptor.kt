package com.teaphy.archs.retrofit.interceptor

import android.annotation.SuppressLint
import android.util.Log
import com.teaphy.archs.base.BaseResponseBean
import okhttp3.*
import java.io.IOException
import java.nio.charset.Charset

/**
 * @desc OkHttp拦截器，用于打印Log日志
 * @author teaphy
 * @time 2018/7/28 上午9:04
 */
class LogCustomInterceptor : Interceptor {
	private val TAG = this@LogCustomInterceptor.javaClass.simpleName


	@SuppressLint("LogNotTimber")
	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {


		val request = chain.request()

		val response = chain.proceed(request)

		// 访问的URL
		val urlRequest = request.url().url()


		if (null == response) {
			val errorResponse = BaseResponseBean(false, "服务器无响应", 500)

			Log.e(TAG, "urlRequest: $urlRequest \n response: $errorResponse")
		}

		val responseBody = response.body()

		val responseBodyString: String = when (response.code()) {
			200 -> response.body()!!.string()
			else -> BaseResponseBean(false, "网络不给力", response.code()).toString()
		}

		Log.e(TAG, "urlRequest: $urlRequest \n response: $responseBodyString")
		return response.newBuilder().body(ResponseBody.create(responseBody?.contentType(), responseBodyString
		)).build()
	}

	private fun bodyEncoded(headers: Headers): Boolean {
		val contentEncoding = headers.get("Content-Encoding")
		return contentEncoding != null && !contentEncoding.equals("identity", ignoreCase = true)
	}

	companion object {

		private val UTF8 = Charset.forName("UTF-8")

		private fun protocol(protocol: Protocol): String {
			return if (protocol == Protocol.HTTP_1_0) "HTTP/1.0" else "HTTP/1.1"
		}
	}
}