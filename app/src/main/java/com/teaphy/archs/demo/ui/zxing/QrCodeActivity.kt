package com.teaphy.archs.demo.ui.zxing

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.teaphy.archs.demo.R

/**
 * ZXing测试 - 扫一扫、识别本地图片、生成二维码图片
 */
class QrCodeActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_qr_code)

		findViewById<Button>(R.id.scan_button).setOnClickListener {
			openActivity(CaptureTestActivity::class.java)
		}

		findViewById<Button>(R.id.local_image_button).setOnClickListener {
			openActivity(IdentifyQrActivity::class.java)
		}

		findViewById<Button>(R.id.generate_image_button).setOnClickListener {
			openActivity(GenerateQrActivity::class.java)
		}
	}

	private fun openActivity(clazz: Class<*>) {
		val intent = Intent(this, clazz)
		startActivity(intent)
	}
}
