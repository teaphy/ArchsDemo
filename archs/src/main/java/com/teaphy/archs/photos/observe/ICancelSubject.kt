package com.teaphy.archs.photos.observe

/**
 * @desc
 * @author teaphy
 * @time 2018/9/30 下午1:49
 */
interface ICancelSubject {
	fun subcribeObserve(observe: ICancelObserve)
	fun unsubcribeObserve(observe: ICancelObserve)
	fun notifyObserver()
}