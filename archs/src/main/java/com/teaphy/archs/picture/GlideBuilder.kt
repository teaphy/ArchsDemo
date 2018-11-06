package com.teaphy.archs.picture

import com.bumptech.glide.request.RequestOptions

/**
 * @desc Glide相关的构建类
 * @author teaphy
 * @time 2018/7/28 下午3:53
 */

public inline fun requestOptionBuilder(builderAction: RequestOptions.()->Unit) : RequestOptions =
		RequestOptions().apply(builderAction)
