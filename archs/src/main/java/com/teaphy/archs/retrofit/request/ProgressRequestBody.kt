package com.teaphy.archs.retrofit.request

import java.io.IOException
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.Okio
import okio.Sink

/**
 * 包装的请求体，处理上传进度
 */
class ProgressRequestBody(// 实际的待包装请求体
		protected var mDelegate: RequestBody, // 进度回调接口
		protected var mListener: ProgressRequestListener) : RequestBody() {
	// 包装完成的BufferedSink
	lateinit var mCountingSink: CountingSink

	override fun contentType(): MediaType? {
		return mDelegate.contentType()
	}

	/**
	 * 重写调用实际的响应体的contentType
	 *
	 * @return MediaType
	 */
	override fun contentLength(): Long {
		try {
			return mDelegate.contentLength()
		} catch (e: IOException) {
			e.printStackTrace()
		}

		return -1
	}

	/**
	 * 重写进行写入
	 *
	 * @param sink BufferedSink
	 * @throws IOException 异常
	 */
	@Throws(IOException::class)
	override fun writeTo(sink: BufferedSink) {
		mCountingSink = CountingSink(sink)
		val bufferedSink = Okio.buffer(mCountingSink)
		//写入
		mDelegate.writeTo(bufferedSink)
		//必须调用flush，否则最后一部分数据可能不会被写入
		bufferedSink.flush()
	}

	inner class CountingSink(delegate: Sink) : ForwardingSink(delegate) {
		private var bytesWritten: Long = 0

		@Throws(IOException::class)
		override fun write(source: Buffer, byteCount: Long) {
			super.write(source, byteCount)
			// 增加当前写入的字节数
			bytesWritten += byteCount
			// 回调
			mListener.onProgress((100f * bytesWritten / contentLength()).toInt())
		}
	}

	interface ProgressRequestListener {
		fun onProgress(progress: Int)
	}
}
