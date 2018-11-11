package com.teaphy.archs.bus

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

/**
 * LiveData构建数据通信总线
 * 注册订阅
 * LiveDataBus.obtainBus()
 *   .with("key_test", String.class)
 *   .observe(this, new Observer<String>() {
 *      @Override public void onChanged(@Nullable String s) {
 *
 *      }
 *   });
 * 发送消息
 * LiveDataBus.get().with("key_test").setValue(s);
 */
class LiveDataBus private constructor() {

    private val bus: MutableMap<String, BusMutableLiveData<Any>> = mutableMapOf()

    companion object {
        private val DEFAULT_BUS = LiveDataBus()

        fun obtainBus(): LiveDataBus {
            return DEFAULT_BUS
        }
    }

    fun <T> with(key: String, type: Class<T>): MutableLiveData<T> {
        if (!bus.containsKey(key)) {
            bus[key] = BusMutableLiveData()
        }
        return bus[key] as MutableLiveData<T>
    }

    fun with(key: String): MutableLiveData<Any> {
        return with(key, Any::class.java)
    }

    private class ObserverWrapper<T>(private val observer: Observer<T>?) : Observer<T> {

        private val isCallOnObserve: Boolean
            get() {
                val stackTrace = Thread.currentThread().stackTrace
                if (stackTrace != null && stackTrace.isNotEmpty()) {
                    for (element in stackTrace) {
                        if ("android.arch.lifecycle.LiveData" == element.className && "observeForever" == element.methodName) {
                            return true
                        }
                    }
                }
                return false
            }

        override fun onChanged(t: T?) {
            if (observer != null) {
                if (isCallOnObserve) {
                    return
                }
                observer.onChanged(t)
            }
        }
    }

    private class BusMutableLiveData<T> : MutableLiveData<T>() {
        private val observerMap = mutableMapOf<Observer<*>, Observer<*>>()

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            super.observe(owner, observer)
            try {
                hook(observer)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        override fun observeForever(observer: Observer<T>) {
            if (!observerMap.containsKey(observer)) {
                observerMap[observer] = ObserverWrapper(observer)
            }
            super.observeForever(observerMap[observer]!! as Observer<T>)
        }

        override fun removeObserver(observer: Observer<T>) {
            var realObserver: Observer<*>? = null
            realObserver = if (observerMap.containsKey(observer)) {
                observerMap.remove(observer)
            } else {
                observer
            }
            super.removeObserver(realObserver!!  as Observer<T>)
        }

        @Throws(Exception::class)
        private fun hook(observer: Observer<T>) {
            //get wrapper's version
            val classLiveData = LiveData::class.java
            val fieldObservers = classLiveData.getDeclaredField("mObservers")
            fieldObservers.isAccessible = true
            val objectObservers = fieldObservers.get(this)
            val classObservers = objectObservers.javaClass
            val methodGet = classObservers.getDeclaredMethod("get", Any::class.java)
            methodGet.isAccessible = true
            val objectWrapperEntry = methodGet.invoke(objectObservers, observer)
            var objectWrapper: Any? = null
            if (objectWrapperEntry is Pair<*, *>) {
                objectWrapper = objectWrapperEntry.second
            }
            if (objectWrapper == null) {
                throw NullPointerException("Wrapper can not be bull!")
            }
            val classObserverWrapper = objectWrapper.javaClass.superclass
            val fieldLastVersion = classObserverWrapper!!.getDeclaredField("mLastVersion")
            fieldLastVersion.isAccessible = true
            //get livedata's version
            val fieldVersion = classLiveData.getDeclaredField("mVersion")
            fieldVersion.isAccessible = true
            val objectVersion = fieldVersion.get(this)
            //set wrapper's version
            fieldLastVersion.set(objectWrapper, objectVersion)
        }
    }
}
