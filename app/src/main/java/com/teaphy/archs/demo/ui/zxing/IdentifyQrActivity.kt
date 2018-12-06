package com.teaphy.archs.demo.ui.zxing

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils
import com.google.zxing.Result
import com.teaphy.archs.photos.PictureSelectorModel
import com.teaphy.archs.photos.PictureSelectors
import com.teaphy.archs.photos.config.PictureSelectConfig
import com.teaphy.archs.photos.entity.LocalMedia
import com.teaphy.archs.photos.loader.IMediaSelectListener
import com.teaphy.archs.picture.PictureHelper
import com.teaphy.archs.demo.R
import com.teaphy.archs.zxing.CodeUtils
import com.teaphy.archs.zxing.IAnalysisCallback
import kotlinx.android.synthetic.main.activity_news_web_view.*

/**
 * 识别本地二维码图片
 */
class IdentifyQrActivity : AppCompatActivity() {

	lateinit var selectButton: Button
	lateinit var identifyButton: Button
	lateinit var valueText: TextView
	lateinit var qrImage: ImageView

	var localMedia: LocalMedia? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_identify_qr)

		selectButton = findViewById(R.id.select_button)
		identifyButton = findViewById(R.id.identify_button)
		valueText = findViewById(R.id.value_text)
		qrImage = findViewById(R.id.qr_image)

		selectButton.setOnClickListener {
			PictureSelectors.create(this@IdentifyQrActivity)
					.openGallery()
					.selectModel(PictureSelectConfig.SelectModel.SINGLE)
					.requestMedias(object : IMediaSelectListener{
						override fun onSelected(listMedias: List<LocalMedia>) {
							localMedia = listMedias[0]
							PictureHelper().loadLocalImage(qrImage, localMedia!!.path)
						}

					})
		}

		identifyButton.setOnClickListener {
			val path = localMedia?.path
			if (!TextUtils.isEmpty(path)) {
				CodeUtils.analyzeBitmap(path!!, object : IAnalysisCallback {
					override fun onAnalysisSuccess(rawResult: Result, barcode: Bitmap?) {
						valueText.text = rawResult.text
						ToastUtils.showShort(rawResult.text)
					}

					override fun onAnalysisFailure() {
						ToastUtils.showShort("识别失败")
					}

				})
			} else {
				ToastUtils.showShort("请选择二维码")
			}
		}
	}
}
