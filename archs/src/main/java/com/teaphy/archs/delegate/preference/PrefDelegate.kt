package com.teaphy.archs.delegate.preference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Base64
import timber.log.Timber

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * SharePreference代理()
 * @param context
 * @param name SharePreference - key
 * @param default 默认值
 * @param listener OnPreferenceValueChanged
 */
class PrefDelegate<T>(val context:Context,
                      val name:String,
                      private val default:T,
                      val listener: OnPreferenceValueChanged<Any>? = null) : ReadWriteProperty<Any?, T> {
    private val TAG = "KPreference"
    //aes 加密后缀
    private val ENCODE_AES_SUFFIX = "_AE0@?@0AAAEA"
    private val prefs by lazy {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).apply {
            registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
                val listeners = Maps[name]
                if (listeners!= null && listeners.isNotEmpty()) {
                    listeners.forEach { it.onChanged(name, getValueFromPreference(sharedPreferences, name, default) as Any) }
                }
            }
        }
    }

    init {
        if (listener != null) {
            if (Maps.containsKey(name)) {
                val listeners = Maps[name]
                if (listeners != null && listeners.isNotEmpty()) {
                    listeners.add(listener)
                } else {
                    val newListeners = ArrayList<OnPreferenceValueChanged<Any>>()
                    newListeners.add(listener)
                    Maps[name] = newListeners
                }
            } else {
                val newListeners = ArrayList<OnPreferenceValueChanged<Any>>()
                newListeners.add(listener)
                Maps[name] = newListeners
            }
        }
    }

    companion object {
        const val PREF_NAME: String = "default"
        val Maps = HashMap<String, MutableList<OnPreferenceValueChanged<Any>>>()
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val value = getPreferenceValue(name, default)

        Timber.e("getValue name is $name value is $value")
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        Timber.e("setValue name is $name value is $value")
        putPreferenceValue(name, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getPreferenceValue(name: String, default: T): T = with(prefs) {
        try {
            val res: Any = when (default) {
                is Long -> getString(name, default.toString()).let { decodeString(it)?.toLong() as Any }
                is String -> getString(name, default).let { decodeString(it) as Any }
                is Int -> getString(name, default.toString()).let { decodeString(it)?.toInt() as Any }
                is Boolean -> getString(name, default.toString()).let { decodeString(it)?.toBoolean() as Any }
                is Float -> getString(name, default.toString()).let { decodeString(it)?.toFloat() as Any }
                else -> throw IllegalArgumentException("This type can be saved into Preferences")
            }
            Timber.e("getPreferenceValue name is $name value is $res")
            val result:T = res as T
            return result

        } catch (e:TypeCastException) {
            return@with default
        }
    }

    @Suppress("UNCHECKED_CAST")
    @SuppressLint("CommitPrefEdits")
    private fun <U> putPreferenceValue(name: String, value: U) = with(prefs.edit()) {
        Timber.e("putPreferenceValue name is $name value is $value")
        when (value) {
            is Long -> putString(name, encodeLong(value))
            is String -> putString(name, encodeString(value))
            is Int -> putString(name, encodeInt(value))
            is Boolean -> putString(name, encodeBoolean(value))
            is Float -> putString(name, encodeFloat(value))
            else -> throw IllegalArgumentException("This type can be saved into KPreferences")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getValueFromPreference(preferences: SharedPreferences,  name: String, default: T): T = with(preferences) {
        val res: Any = when (default) {
            is Long -> getString(name, default.toString()).let { decodeString(it)?.toLong() as Any }
            is String -> getString(name, default).let { decodeString(it) as Any }
            is Int -> getString(name, default.toString()).let { decodeString(it)?.toInt() as Any }
            is Boolean -> getString(name, default.toString()).let { decodeString(it)?.toBoolean() as Any }
            is Float -> getString(name, default.toString()).let { decodeString(it)?.toFloat() as Any }
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        return res as T
    }

    /**
     * 解密
     *
     * @param value
     * @return
     */
    private fun decodeString(value: String): String? {
        if (TextUtils.isEmpty(value)) {
            return value
        }
        return if (value.endsWith(ENCODE_AES_SUFFIX)) {
            val index = value.indexOf(ENCODE_AES_SUFFIX)
            val decodeString = value.substring(0, index)
            String(Base64.decode(decodeString, Base64.DEFAULT))
        } else {
            value
        }
    }

    /**
     * 加密
     *
     * @param value
     * @return
     */
    private fun encodeString(value: String): String? {
        if (TextUtils.isEmpty(value)) {
            return value
        }
        // 启用AES加密
        val encodeString = Base64.encodeToString(value.toByteArray(), Base64.DEFAULT)//AESUtils.encrypt(value)
        //加密失败返回null，不做处理返回原字符串
        return if (TextUtils.isEmpty(encodeString)) {
            value
        } else encodeString + ENCODE_AES_SUFFIX
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveString(preferences: SharedPreferences, key:String, str:String) {
        with(preferences.edit()) {
            putString(key, str)
        }.apply()
    }

    private fun encodeInt(value:Int):String? {
        return encodeString(value.toString())
    }

    private fun encodeLong(value:Long):String? {
        return encodeString(value.toString())
    }

    private fun encodeFloat(value:Float):String? {
        return encodeString(value.toString())
    }

    private fun encodeBoolean(value:Boolean) :String? {
        return encodeString(value.toString())
    }

}