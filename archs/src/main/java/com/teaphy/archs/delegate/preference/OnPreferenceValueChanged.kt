package com.teaphy.archs.delegate.preference

interface OnPreferenceValueChanged<in T> {
    fun onChanged(name:String, value:T)
}
