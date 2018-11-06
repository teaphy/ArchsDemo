package com.teaphy.archs.photos.ui

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.teaphy.archs.picture.PictureHelper
import com.teaphy.archs.view.IItemCallback
import com.teaphy.archs.R
import com.teaphy.archs.photos.entity.LocalMedia

/**
 * @desc
 * @author teaphy
 * @time 2018/9/30 下午3:17
 */
class SingleMediaAdapter(private val listImage: List<LocalMedia>) : PagerAdapter() {

	var itemClickListener: IItemCallback<LocalMedia>? = null

	override fun isViewFromObject(view: View, any: Any): Boolean {
		return view == any
	}

	override fun getCount(): Int {
		return listImage.size
	}

	override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
		container.removeView(any as View)
	}

	override fun instantiateItem(container: ViewGroup, position: Int): Any {
		val contentView = LayoutInflater.from(container.context)
				.inflate(R.layout.item_single_media, container, false)
		val previewImage: ImageView = contentView.findViewById(R.id.previewImage)
		val localMedia = listImage[position]

		PictureHelper().loadLocalImage(previewImage, localMedia.path)
		container.addView(contentView, 0)

		contentView.setOnClickListener {
			itemClickListener?.onItemClick(localMedia)
		}

		return contentView
	}
}