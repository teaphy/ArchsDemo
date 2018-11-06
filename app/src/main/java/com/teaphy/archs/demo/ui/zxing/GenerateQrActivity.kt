package com.teaphy.archs.demo.ui.zxing

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.ToastUtils
import com.teaphy.archs.demo.R
import com.teaphy.testzxing.zxing.CodeUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.reflect.Field

/**
 * 生成二维码图片
 */
class GenerateQrActivity : AppCompatActivity() {

	lateinit var contentEdit: EditText
	lateinit var generateButton: Button
	lateinit var qrImage: ImageView

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_generate_qr)

		contentEdit = findViewById(R.id.content_edit)
		generateButton = findViewById(R.id.generate_button)
		qrImage = findViewById(R.id.qr_image)

		generateButton.setOnClickListener {

			val content = contentEdit.text.toString()

			if (TextUtils.isEmpty(content)) {
				ToastUtils.showShort("请输入二维码内容")
			} else {
				val logo = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher)
				val resultBitMap = CodeUtils.createImage(content,
						SizeUtils.dp2px(144f),
						SizeUtils.dp2px(144f),
						logo)
				qrImage.setImageBitmap(resultBitMap)
			}
		}
	}
}
