package com.teaphy.archs.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel(), Observable {

	private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

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

	// DataBinding
	override fun addOnPropertyChangedCallback(
			callback: Observable.OnPropertyChangedCallback) {
		callbacks.add(callback)
	}

	override fun removeOnPropertyChangedCallback(
			callback: Observable.OnPropertyChangedCallback) {
		callbacks.remove(callback)
	}

	/**
	 * Notifies observers that all properties of this instance have changed.
	 */
	fun notifyChange() {
		callbacks.notifyCallbacks(this, 0, null)
	}

	/**
	 * Notifies observers that a specific property has changed. The getter for the
	 * property that changes should be marked with the @Bindable annotation to
	 * generate a field in the BR class to be used as the fieldId parameter.
	 *
	 * @param fieldId The generated BR id for the Bindable field.
	 */
	fun notifyPropertyChanged(fieldId: Int) {
		callbacks.notifyCallbacks(this, fieldId, null)
	}
}