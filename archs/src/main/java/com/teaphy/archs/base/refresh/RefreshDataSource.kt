package com.teaphy.archs.base.refresh

/**
 * @desc 用于更新的数据源
 * @author teaphy
 * @time 2018/8/31 下午6:33
 *
 * @param T 数据类型
 * @param isSuccess 是否获取数据
 */
data class RefreshDataSource<T>(val isSuccess: Boolean = false, val data: List<T>? = null,
                                val totalItem: Int = 0, val totalPage: Int = 0)