package com.teaphy.archs.zxing

import android.graphics.Bitmap
import android.os.Bundle
import com.google.zxing.Result

/**
 * @desc 条形码解析回调
 * @author teaphy
 * @time 2018/9/4 下午2:28
 */
interface IAnalysisCallback {

	fun onAnalysisSuccess(rawResult: Result, barcode: Bitmap?)

	fun onAnalysisFailure()
}