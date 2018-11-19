package com.teaphy.archs.picture

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.umeng.socialize.utils.DeviceConfig.context
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.circleCrop



/**
 * @desc
 * @author teaphy
 * @time 2018/8/23 下午2:32
 */
class GlideStrategy : IPictureStrategy {


    /**
     * 加载本地图片资源
     * @author teaphy
     * @date 2018/7/28 下午3:32
     */
    override fun loadLocalImage(imageView: ImageView, path: String) {
        Glide.with(imageView)
                .asBitmap()
                .load(path)
                .into(imageView)
    }

    /**
     * 加载本地图片资源，且 效果为边角带有弧度
     * @author teaphy
     * @date 2018/7/28 下午3:33
     */
    override fun loadLocalRoundImage(imageView: ImageView, radius: Int, path: String) {
        val requestOptions = RequestOptions().transform(RoundedCorners(radius))
        Glide.with(imageView)
                .asBitmap()
                .load(path)
                .apply(requestOptions)
                .into(imageView)
    }

    /**
     * 加载本地图片资源，其效果为圆形图片
     * @author teaphy
     * @date 2018/7/28 下午3:34
     * @param
     * @return
     */
    override fun loadLocalCircleImage(imageView: ImageView, path: String) {
        val requestOptions = RequestOptions.circleCropTransform()
        Glide.with(imageView)
                .asBitmap()
                .load(path)
                .apply(requestOptions)
                .into(imageView)
    }

    /**
     * 加载本地图片资源
     * @author teaphy
     * @date 2018/7/28 下午3:32
     */
    override fun loadLocalImage(imageView: ImageView, @DrawableRes imgRes: Int) {
        Glide.with(imageView)
                .load(imgRes)
                .into(imageView)
    }

    /**
     * 加载本地图片资源，且 效果为边角带有弧度
     * @author teaphy
     * @date 2018/7/28 下午3:33
     */
    override fun loadLocalRoundImage(imageView: ImageView, @DrawableRes imgRes: Int, radius: Int) {
        val requestOptions = RequestOptions().transform(RoundedCorners(radius))
        Glide.with(imageView)
                .load(imgRes)
                .apply(requestOptions)
                .into(imageView)
    }

    /**
     * 加载本地图片资源，其效果为圆形图片
     * @author teaphy
     * @date 2018/7/28 下午3:34
     * @param
     * @return
     */
    override fun loadLocalCircleImage(imageView: ImageView, @DrawableRes imgRes: Int) {
        val requestOptions = RequestOptions.circleCropTransform()
        Glide.with(imageView)
                .load(imgRes)
                .apply(requestOptions)
                .into(imageView)
    }

    /**
     * 加载远程图片资源
     * @author teaphy
     * @date 2018/7/28 下午3:32
     */
    override fun loadRemoteImage(imageView: ImageView, urlImage: String) {


        val requestOptions = requestOptionBuilder {
            diskCacheStrategy(DiskCacheStrategy.ALL)
            fitCenter()
        }

        Glide.with(imageView)
                .load(urlImage)
                .apply(requestOptions)
                .into(imageView)
    }

    /**
     * 加载远程图片资源,带有占位图
     * @author teaphy
     * @date 2018/7/28 下午3:32
     */
    override fun loadRemoteImage(imageView: ImageView, urlImage: String,
                                 @DrawableRes placeHolder: Int, @DrawableRes errorHolder: Int) {
        if (TextUtils.isEmpty(urlImage)) {
            Glide.with(imageView)
                    .load(errorHolder)
                    .into(imageView)
        } else {
            val requestOptions = requestOptionBuilder {
                diskCacheStrategy(DiskCacheStrategy.ALL)
                fitCenter()
                placeholder(placeHolder)
                dontAnimate()
                error(errorHolder)
            }


            val placeRequest = Glide.with(imageView)
                    .load(placeHolder)

            Glide.with(imageView)
                    .load(urlImage)
                    .thumbnail(placeRequest)
                    .apply(requestOptions)
                    .into(imageView)
        }
    }


    override fun loadRemoteImage(imageView: ImageView, urlImage: String, placeHolder: Drawable, errorHolder: Drawable) {
        if (TextUtils.isEmpty(urlImage)) {
            Glide.with(imageView)
                    .load(errorHolder)
                    .into(imageView)
        } else {
            val requestOptions = requestOptionBuilder {
                diskCacheStrategy(DiskCacheStrategy.ALL)
                fitCenter()
                placeholder(placeHolder)
                error(errorHolder)
                dontAnimate()
            }


            val placeRequest = Glide.with(imageView)
                    .load(placeHolder)

            Glide.with(imageView)
                    .load(urlImage)
                    .thumbnail(placeRequest)
                    .apply(requestOptions)
                    .into(imageView)
        }

    }

    /**
     * 加载本地图片资源，且 效果为边角带有弧度
     * @author teaphy
     * @date 2018/7/28 下午3:33
     */
    override fun loadRemoteRoundImage(imageView: ImageView, urlImage: String, radius: Int) {
        val requestOptions = requestOptionBuilder {
            diskCacheStrategy(DiskCacheStrategy.ALL)
            fitCenter()
            transform(RoundedCorners(radius))
        }

        Glide.with(imageView)
                .load(urlImage)
                .apply(requestOptions)
                .into(imageView)
    }

    /**
     * 加载远程图片资源，带有占位图，且 效果为边角带有弧度
     * @author teaphy
     * @date 2018/7/28 下午3:33
     */
    override fun loadRemoteRoundImage(imageView: ImageView, urlImage: String, radius: Int,
                                      @DrawableRes placeHolder: Int, @DrawableRes errorHolder: Int) {
        if (TextUtils.isEmpty(urlImage)) {
            Glide.with(imageView)
                    .load(errorHolder)
                    .into(imageView)
        } else {
            val requestOptions = requestOptionBuilder {
                diskCacheStrategy(DiskCacheStrategy.ALL)
                fitCenter()
                transform(RoundedCorners(radius))
                placeholder(placeHolder)
                error(errorHolder)
                dontAnimate()
            }

            val placeRequest = Glide.with(imageView)
                    .load(placeHolder)

            Glide.with(imageView)
                    .load(urlImage)
                    .thumbnail(placeRequest)
                    .apply(requestOptions)
                    .into(imageView)
        }

    }

    override fun loadRemoteRoundImage(imageView: ImageView, urlImage: String, radius: Int,
                                      placeHolder: Drawable, errorHolder: Drawable) {

        if (TextUtils.isEmpty(urlImage)) {
            Glide.with(imageView)
                    .load(errorHolder)
                    .into(imageView)
        } else {
            val requestOptions = requestOptionBuilder {
                diskCacheStrategy(DiskCacheStrategy.ALL)
                fitCenter()
                transform(RoundedCorners(radius))
                placeholder(placeHolder)
                error(errorHolder)
                dontAnimate()
            }

            val placeRequest = Glide.with(imageView)
                    .load(placeHolder)

            Glide.with(imageView)
                    .load(urlImage)
                    .thumbnail(placeRequest)
                    .apply(requestOptions)
                    .into(imageView)
        }

    }

    /**
     * 加载远程图片资源，其效果为圆形图片
     * @author teaphy
     * @date 2018/7/28 下午3:34
     * @param
     * @return
     */
    override fun loadRemoteCircleImage(imageView: ImageView, urlImage: String) {
        val requestOptions = requestOptionBuilder {
            circleCrop()
            autoClone()
            diskCacheStrategy(DiskCacheStrategy.ALL)
        }

        Glide.with(imageView)
                .load(urlImage)
                .apply(requestOptions)
                .into(imageView)
    }


    /**
     * 加载远程图片资源，带有占位图，其效果为圆形图片
     * @author teaphy
     * @date 2018/7/28 下午3:34
     * @param
     * @return
     */
    override fun loadRemoteCircleImage(imageView: ImageView, urlImage: String,
                                       @DrawableRes placeHolder: Int, @DrawableRes errorHolder: Int) {
        if (TextUtils.isEmpty(urlImage)) {
            Glide.with(imageView)
                    .load(errorHolder)
                    .into(imageView)
        } else {
            val requestOptions = requestOptionBuilder {
                diskCacheStrategy(DiskCacheStrategy.ALL)
                circleCrop()
                autoClone()
                placeholder(placeHolder)
                error(errorHolder)
                dontAnimate()
            }
            val placeRequest = Glide.with(imageView)
                    .load(placeHolder)

            Glide.with(imageView)
                    .load(urlImage)
                    .thumbnail(placeRequest)
                    .apply(requestOptions)
                    .into(imageView)
        }

    }

    override fun loadRemoteCircleImage(imageView: ImageView, urlImage: String,
                                       placeHolder: Drawable, errorHolder: Drawable) {
        if (TextUtils.isEmpty(urlImage)) {
            Glide.with(imageView)
                    .load(errorHolder)
                    .into(imageView)
        } else {

            val requestOptions = requestOptionBuilder {
                diskCacheStrategy(DiskCacheStrategy.ALL)
                placeholder(placeHolder)
                error(errorHolder)
                dontAnimate()
                circleCrop()
            }

            val placeRequest = Glide.with(imageView)
                    .load(placeHolder)

            Glide.with(imageView)
                    .load(urlImage)
                    .thumbnail(placeRequest)
                    .apply(requestOptions)
                    .into(imageView)
        }
    }
}