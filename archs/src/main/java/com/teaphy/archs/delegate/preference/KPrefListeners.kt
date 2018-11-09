package com.teaphy.archs.delegate.preference


class KPrefListeners<T> private constructor(){
    private val mListenersMap:HashMap<String, MutableList<OnPreferenceValueChanged<T>>> = HashMap()

    fun contains(name:String):Boolean {
        return mListenersMap.containsKey(name)
    }

    fun add(name: String, list: MutableList<OnPreferenceValueChanged<T>>) {
        mListenersMap.put(name, list)
    }

    fun add(name: String, listener: OnPreferenceValueChanged<T>) {
        if (mListenersMap.containsKey(name)) {
            val l = mListenersMap[name]
            if (l != null) {
                if (l.isNotEmpty()) {
                    l.add(listener)
                }
            } else {
                val listeners = ArrayList<OnPreferenceValueChanged<T>>()
                listeners.add(listener)
                mListenersMap.put(name, listeners)
            }
        } else {
            val listeners = ArrayList<OnPreferenceValueChanged<T>>()
            listeners.add(listener)
            mListenersMap.put(name, listeners)
        }
    }

    fun get(name: String):MutableList<OnPreferenceValueChanged<T>>? {
        return mListenersMap[name]
    }

    companion object {
        fun <T>newInstance(): KPrefListeners<T> {

            return KPrefListeners()
        }
    }
}