package com.teaphy.archs.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @desc ViewPagerAdapter 适用于ViewPager + TabLayout
 * @author teaphy
 * @time 2018/8/21 下午4:26
 */
class CommonPagerAdapter(fm: FragmentManager,private val listFragment: List<Fragment>,private val listTitle: List<String>) : FragmentPagerAdapter(fm) {


	override fun getItem(position: Int): Fragment {
		return listFragment[position]
	}

	override fun getCount(): Int {
		return listFragment.size
	}

	override fun getPageTitle(position: Int): CharSequence? {
		return listTitle[position]
	}
}