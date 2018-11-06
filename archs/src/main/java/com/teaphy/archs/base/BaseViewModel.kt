package com.teaphy.archs.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

	private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
	private lateinit var loadingLiveData: MutableLiveData<Boolean>

	override fun onCleared() {
		super.onCleared()

		if (!mCompositeDisposable.isDisposed) {
			mCompositeDisposable.dispose()
		}
	}

	fun addDisposable(disposable: Disposable) {
		mCompositeDisposable.add(disposable)
	}

	/**
	  * 用来控制是否显示Loading
	  * @author teaphy
	  * @date 2018/7/23 上午10:46
	  */
	fun updateLoadingStatus(isShow: Boolean) {
		loadingLiveData.postValue(isShow)
	}

	fun getLoadingLiveData(): LiveData<Boolean> {
		loadingLiveData = MutableLiveData()
		return loadingLiveData
	}
}