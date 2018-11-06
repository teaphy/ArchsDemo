/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.teaphy.testzxing.zxing

import com.google.zxing.ResultPoint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.support.v4.app.ActivityCompat
import android.util.AttributeSet
import android.view.View
import com.teaphy.archs.R
import com.teaphy.testzxing.zxing.camera.CameraManager

import java.util.ArrayList

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder rectangle and partial
 * transparency outside it, as well as the laser scanner animation and result points.
 *
 * 此视图覆盖在相机预览的顶部。 它在其外部添加了取景器矩形和部分ansparency，以及激光扫描仪动画和结果点。
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
class ViewfinderView// This constructor is used when the class is built from an XML resource.
(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

	private var cameraManager: CameraManager? = null
	private val paint: Paint
	private val traAnglePaint: Paint
	private var resultBitmap: Bitmap? = null
	private val maskColor: Int
	private val resultColor: Int
	private val laserColor: Int
	private val resultPointColor: Int
	private var scannerAlpha: Int = 0
	private var possibleResultPoints: MutableList<ResultPoint>? = null
	private var lastPossibleResultPoints: List<ResultPoint>? = null
	private val triAngleLength = dp2px(20) //每个角的点距离
	private val triAngleWidth = dp2px(4) //每个角的点宽度
	private var triAngleColor: Int = Color.parseColor("#76EE00")
		set(value) {
			traAnglePaint.color = value
			field = value
		}

	private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)

	private var lineOffsetCount = 0

	init {

		// Initialize these once for performance rather than calling them every time in onDraw().
		paint = Paint(Paint.ANTI_ALIAS_FLAG)

		// 初始化traAnglePaint
		traAnglePaint = Paint(Paint.ANTI_ALIAS_FLAG)
		with(traAnglePaint) {
			color = triAngleColor
			strokeWidth = triAngleWidth.toFloat()
			style = Paint.Style.STROKE
		}

		val resources = resources
		maskColor = resources.getColor(R.color.viewfinder_mask)
		resultColor = resources.getColor(R.color.result_view)
		laserColor = resources.getColor(R.color.viewfinder_laser)
		resultPointColor = resources.getColor(R.color.possible_result_points)
		scannerAlpha = 0
		possibleResultPoints = ArrayList(5)
		lastPossibleResultPoints = null

		if (attrs != null) {

		}
	}

	fun setCameraManager(cameraManager: CameraManager) {
		this.cameraManager = cameraManager
	}

	@SuppressLint("DrawAllocation")
	public override fun onDraw(canvas: Canvas) {
		if (cameraManager == null) {
			return  // not ready yet, early draw before done configuring
		}
		val frame = cameraManager!!.framingRect
		val previewFrame = cameraManager!!.framingRectInPreview
		if (frame == null || previewFrame == null) {
			return
		}
		val width = canvas.width
		val height = canvas.height

		// Draw the exterior (i.e. outside the framing rect) darkened
		paint.color = if (resultBitmap != null) resultColor else maskColor
		canvas.drawRect(0f, 0f, width.toFloat(), frame.top.toFloat(), paint)
		canvas.drawRect(0f, frame.top.toFloat(), frame.left.toFloat(), (frame.bottom + 1).toFloat(), paint)
		canvas.drawRect((frame.right + 1).toFloat(), frame.top.toFloat(), width.toFloat(), (frame.bottom + 1).toFloat(), paint)
		canvas.drawRect(0f, (frame.bottom + 1).toFloat(), width.toFloat(), height.toFloat(), paint)

		// 四个角落的三角
		val leftTopPath = Path()
		leftTopPath.moveTo((frame.left + triAngleLength).toFloat(), (frame.top + triAngleWidth / 2).toFloat())
		leftTopPath.lineTo((frame.left + triAngleWidth / 2).toFloat(), (frame.top + triAngleWidth / 2).toFloat())
		leftTopPath.lineTo((frame.left + triAngleWidth / 2).toFloat(), (frame.top + triAngleLength).toFloat())
		canvas.drawPath(leftTopPath, traAnglePaint)

		val rightTopPath = Path()
		rightTopPath.moveTo((frame.right - triAngleLength).toFloat(), (frame.top + triAngleWidth / 2).toFloat())
		rightTopPath.lineTo((frame.right - triAngleWidth / 2).toFloat(), (frame.top + triAngleWidth / 2).toFloat())
		rightTopPath.lineTo((frame.right - triAngleWidth / 2).toFloat(), (frame.top + triAngleLength).toFloat())
		canvas.drawPath(rightTopPath, traAnglePaint)

		val leftBottomPath = Path()
		leftBottomPath.moveTo((frame.left + triAngleWidth / 2).toFloat(), (frame.bottom - triAngleLength).toFloat())
		leftBottomPath.lineTo((frame.left + triAngleWidth / 2).toFloat(), (frame.bottom - triAngleWidth / 2).toFloat())
		leftBottomPath.lineTo((frame.left + triAngleLength).toFloat(), (frame.bottom - triAngleWidth / 2).toFloat())
		canvas.drawPath(leftBottomPath, traAnglePaint)

		val rightBottomPath = Path()
		rightBottomPath.moveTo((frame.right - triAngleLength).toFloat(), (frame.bottom - triAngleWidth / 2).toFloat())
		rightBottomPath.lineTo((frame.right - triAngleWidth / 2).toFloat(), (frame.bottom - triAngleWidth / 2).toFloat())
		rightBottomPath.lineTo((frame.right - triAngleWidth / 2).toFloat(), (frame.bottom - triAngleLength).toFloat())
		canvas.drawPath(rightBottomPath, traAnglePaint)


		if (resultBitmap != null) {
			// Draw the opaque result bitmap over the scanning rectangle
			paint.alpha = CURRENT_POINT_OPACITY
			canvas.drawBitmap(resultBitmap!!, null, frame, paint)
		} else {
			//循环划线，从上到下
			if (lineOffsetCount > frame.bottom - frame.top - dp2px(10)) {
				lineOffsetCount = 0
			} else {
				lineOffsetCount += 2
				// 移动的线
				val lineRect = Rect()
				lineRect.left = frame.left
				lineRect.top = frame.top + lineOffsetCount
				lineRect.right = frame.right
				lineRect.bottom = frame.top + dp2px(2) + lineOffsetCount
				val scanLine = (ActivityCompat.getDrawable(context, R.mipmap.afcs_ic_scan_line)) as BitmapDrawable
				canvas.drawBitmap(scanLine.bitmap, null, lineRect, linePaint)
			}

			postInvalidateDelayed (10L, frame.left, frame.top, frame.right, frame.bottom)

		}
	}

	fun drawViewfinder() {
		val resultBitmap = this.resultBitmap
		this.resultBitmap = null
		resultBitmap?.recycle()
		invalidate()
	}

	/**
	 * Draw a bitmap with the result points highlighted instead of the live scanning display.
	 *
	 * @param barcode An image of the decoded barcode.
	 */
	fun drawResultBitmap(barcode: Bitmap) {
		resultBitmap = barcode
		invalidate()
	}

	fun addPossibleResultPoint(point: ResultPoint) {
		val points = possibleResultPoints
		points!!.add(point)
		val size = points.size
		if (size > MAX_RESULT_POINTS) {
			// trim it
			points.subList(0, size - MAX_RESULT_POINTS / 2).clear()
		}
	}

	companion object {

		private val SCANNER_ALPHA = intArrayOf(0, 64, 128, 192, 255, 192, 128, 64)
		private val ANIMATION_DELAY = 80L
		private val CURRENT_POINT_OPACITY = 0xA0
		private val MAX_RESULT_POINTS = 20
		private val POINT_SIZE = 6
	}

	private fun dp2px(dp: Int): Int {
		val density = context.resources.displayMetrics.density
		return (dp * density + 0.5f).toInt()
	}
}
