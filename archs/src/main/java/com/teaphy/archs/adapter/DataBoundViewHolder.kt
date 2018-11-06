package com.teaphy.archs.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * 包含ViewDataBinding的RecyclerView.ViewHolder通用类
 */
open class DataBoundViewHolder<out T : ViewDataBinding> constructor(val binding: T) :
    RecyclerView.ViewHolder(binding.root)
