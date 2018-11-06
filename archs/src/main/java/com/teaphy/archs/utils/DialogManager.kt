package com.teaphy.archs.utils

import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction


/**
 * DialogFragment 管理类
 *
 * @autor teaphy
 * Created at 2016/8/18 0:07
 */
object DialogManager {

	val TAG = "DIALOG"

	/**
	 * @param df
	 * @param fm
	 * @param tag DialogFragmeng TAG
	 * @desc dialogFragment显示
	 * @autor teaphy
	 * @time 2016/8/18
	 */
	fun displayDialog(df: DialogFragment, fm: FragmentManager, tag: String) {
		df.show(fm, tag)
	}

	fun displayDialog(df: DialogFragment, fm: FragmentManager) {
		df.show(fm, TAG)
	}


	/**
	 * @param fm  FragmentManager
	 * @param tag DialogFragmeng TAG
	 * @desc 取消dialogFragment显示，并将DialogFragment从Fragment栈中删除
	 * @autor teaphy
	 * @time 2016/8/18
	 */
	fun dismissDialog(fm: FragmentManager, tag: String) {
		val ft = fm.beginTransaction()
		val df = fm.findFragmentByTag(tag) as? DialogFragment
		if (null != df) {
			df.dismiss()
			ft.remove(df)
			ft.commitAllowingStateLoss()
		}
	}

	fun dismissDialog(fm: FragmentManager) {
		val ft = fm.beginTransaction()
		val df = fm.findFragmentByTag(TAG) as? DialogFragment
		if (null != df) {
			df.dismiss()
			ft.remove(df)
			ft.commitAllowingStateLoss()
		}
	}
}
