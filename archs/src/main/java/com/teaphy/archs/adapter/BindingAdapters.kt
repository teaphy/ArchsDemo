package com.teaphy.archs.adapter

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.teaphy.archs.picture.PictureHelper


/**
 * 自定义Setter,相当于自定义View的属性
 */
object BindingAdapters {

    /**
     * 设置Progress的进度 必须设置 max和progress
     */
    @BindingAdapter(value = ["android:max", "android:progress"], requireAll = true)
    @JvmStatic
    fun updateProgress(progressBar: ProgressBar, max: Int, progress: Int) {
        progressBar.max = max
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(progress, false)
        } else {
            progressBar.progress = progress
        }
    }

    /**
     * 设置加载图片
     */
    @BindingAdapter(value = ["app:remoteSrc"])
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        PictureHelper().loadRemoteImage(imageView, url)
    }

    /**
     * 设置加载圆图片
     */
    @BindingAdapter(value = ["app:remoteCircleSrc"])
    @JvmStatic
    fun loadCircleImage(imageView: ImageView, url: String) {
        PictureHelper().loadRemoteCircleImage(imageView, url)
    }

    /**
     * 设置加载边角带有弧度的图片 必须指定边角的弧度
     */
    @BindingAdapter(value = ["app:remoteRoundSrc", "app:remoteRoundRadius"])
    @JvmStatic
    fun loadRoundImage(imageView: ImageView, url: String, roundRadius: Int) {
        PictureHelper().loadRemoteRoundImage(imageView, url, roundRadius)
    }

    /**
     * 设置加载图片 必须设置图片的 imageUrl、placeHolder(占位图)和error(错误提示图)
     */
    @BindingAdapter(value = ["app:remoteHolderSrc", "app:remoteHolderPlace", "app:remoteHolderError"])
    @JvmStatic
    fun loadImageWithHolder(imageView: ImageView, url: String,
                            placeholder: Drawable, error: Drawable) {
        PictureHelper().loadRemoteImage(imageView, url, placeholder, error)
    }

    /**
     * 设置加载圆图片 必须设置图片的 imageUrl、placeHolder(占位图)和error(错误提示图)
     */
    @BindingAdapter(value = ["app:remoteCircleHolderSrc", "app:remoteCircleHolderPlace", "app:remoteCircleHolderError"])
    @JvmStatic
    fun loadCircleImageWithHolder(imageView: ImageView, url: String,
                                  placeholder: Drawable, error: Drawable) {
        PictureHelper().loadRemoteCircleImage(imageView, url, placeholder, error)
    }

    /**
     * 设置加载图片 必须设置图片的 imageUrl、placeHolder(占位图)和error(错误提示图)以及边角弧度
     */
    @BindingAdapter(value = ["app:remoteRoundHolderSrc",
        "app:remoteRoundHolderRadius",
        "app:remoteRoundHolderPlace",
        "app:remoteRoundHolderError"])
    @JvmStatic
    fun loadRoundImageWithHolder(imageView: ImageView, url: String, roundRadius: Int,
                                 placeholder: Drawable, error: Drawable) {
        PictureHelper().loadRemoteRoundImage(imageView, url, roundRadius, placeholder, error)
    }


    /**
     * Makes the View [View.INVISIBLE] unless the condition is met.
     */
    @Suppress("unused")
    @BindingAdapter("invisibleUnless")
    @JvmStatic
    fun invisibleUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }

    /**
     * Makes the View [View.GONE] unless the condition is met.
     */
    @Suppress("unused")
    @BindingAdapter("goneUnless")
    @JvmStatic
    fun goneUnless(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }


}