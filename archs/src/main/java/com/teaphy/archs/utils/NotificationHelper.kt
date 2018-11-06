package com.teaphy.archs.utils

import android.content.Context.NOTIFICATION_SERVICE

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build.VERSION_CODES
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationCompat.Builder
import android.support.v4.app.NotificationManagerCompat
import com.blankj.utilcode.util.AppUtils

/**
 * 通知辅助类
 */
object NotificationHelper {

	/**
	 * 初始化 默认Channel
	 * @param context
	 */
	@RequiresApi(api = VERSION_CODES.O)
	fun initDefaultChannel(context: Context) {

		// 用户可见的频道名称
		// 当系统区域设置更改时，可以通过监听Intent.ACTION_LOCALE_CHANGED广播来重命名此频道。
		val name = AppUtils.getAppName()
		// 用户可见的频道描述
		val description = "及时通知信息"
		// 默认ID为包名
		// 默认名称为App名称
		initChannel(context, AppUtils.getAppPackageName(), name, description)
	}

	/**
	 * 初始化channel
	 */
	@RequiresApi(api = VERSION_CODES.O)
	fun initChannel(context: Context, channelId: String, name: String, description: String) {
		// 它表示了该频道的重要性级别，控制发布到此频道的中断通知的方式
		val importance = NotificationManager.IMPORTANCE_DEFAULT
		// 创建NotificationChannel实例
		val channel = NotificationChannel(channelId, name, importance)
		// 设置用户可见的频道描述
		channel.description = description

		val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
		// 向系统注册当前应用的通知渠道
		notificationManager.createNotificationChannel(channel)
	}

	/**
	 * 发布 普通通知(默认channelId为包名)
	 * @param context
	 * @param notificationId
	 * @param largeIcon
	 * @param smallIcon
	 * @param title
	 * @param content
	 */
	fun publishNotification(context: Context, notificationId: Int,largeIcon: Bitmap,
	                        @DrawableRes smallIcon: Int, title: String, content: String) {
		val builder = Builder(context, AppUtils.getAppPackageName())
		builder.setContentTitle(title)
				.setContentText(content)
				.setLargeIcon(largeIcon)
				.setSmallIcon(smallIcon).priority = NotificationCompat.PRIORITY_HIGH

		// 显示通知
		notifyNotification(context, notificationId, builder)
	}

	/**
	 * 发布 普通通知
	 * @param context
	 * @param notificationId
	 * @param channelId
	 * @param largeIcon
	 * @param smallIcon
	 * @param title
	 * @param content
	 */
	fun publishNotification(context: Context, notificationId: Int, channelId: String,
	                        largeIcon: Bitmap, @DrawableRes smallIcon: Int, title: String, content: String) {
		val builder = Builder(context, channelId)
		builder.setContentTitle(title)
				.setContentText(content)
				.setLargeIcon(largeIcon)
				.setSmallIcon(smallIcon).priority = NotificationCompat.PRIORITY_HIGH

		// 显示通知
		notifyNotification(context, notificationId, builder)
	}

	/**
	 * 发布 带进度条的通知
	 * @param context
	 * @param notificationId
	 * @param channelId
	 * @param largeIcon
	 * @param smallIcon
	 * @param title
	 * @param content
	 */
	fun publishProgressNotification(context: Context,
	                                notificationId: Int,
	                                channelId: String,
	                                largeIcon: Bitmap,
	                                @DrawableRes smallIcon: Int,
	                                title: String,
	                                content: String,
	                                max: Int,
	                                pro: Int,
	                                indeterminate: Boolean) {
		val builder = Builder(context, channelId)
		builder.setContentTitle(title)
				.setContentText(content)
				.setLargeIcon(largeIcon)
				.setSmallIcon(smallIcon)
				.setProgress(max, pro, indeterminate).priority = NotificationCompat.PRIORITY_HIGH
		notifyNotification(context, notificationId, builder)
	}

	/**
	 * 显示通知
	 * @param context
	 * @param notificationId
	 * @param builder
	 */
	private fun notifyNotification(context: Context, notificationId: Int, builder: Builder) {
		// 显示通知
		val manager = NotificationManagerCompat.from(context)
		manager.notify(notificationId, builder.build())
	}
}
