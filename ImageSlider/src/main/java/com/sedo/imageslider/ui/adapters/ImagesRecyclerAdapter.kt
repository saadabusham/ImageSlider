package com.sedo.imageslider.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.sedo.imageslider.databinding.RowImageBinding
import com.sedo.imageslider.ui.base.binding.setImageFromUrl
import com.sedo.imageslider.util.listeners.OnItemClickListener

class ImagesRecyclerAdapter(
    private val mContext: Context,
    private val resources: Boolean,
    private val sliderArrayListString: List<String>?,
    private val sliderArrayListResource: List<Int>?
) : PagerAdapter() {
    var itemClickListener: OnItemClickListener? = null
    override fun getCount(): Int = if (resources)
        sliderArrayListResource?.size ?: 0
    else
        sliderArrayListString?.size ?: 0

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = (mContext as Activity).layoutInflater
        val cellSliderImageBinding = RowImageBinding
            .inflate(inflater, container, false)
        if (resources)
            sliderArrayListResource?.get(position)?.let {
                cellSliderImageBinding.ivImage.setImageResource(
                    it
                )
            }
        else
            cellSliderImageBinding.ivImage.setImageFromUrl(sliderArrayListString?.get(position))

        container.addView(cellSliderImageBinding.root, 0)
        cellSliderImageBinding.root.setOnClickListener {
            itemClickListener?.onItemClick(
                cellSliderImageBinding.ivImage,
                position,
                if (resources)
                    sliderArrayListResource?.get(position)!!
                else
                    sliderArrayListString?.get(position)!!
            )
        }
        cellSliderImageBinding.root.setOnLongClickListener {
            itemClickListener?.onItemLongClick(
                cellSliderImageBinding.ivImage,
                position,
                if (resources)
                    sliderArrayListResource?.get(position)!!
                else
                    sliderArrayListString?.get(position)!!
            )
            return@setOnLongClickListener true
        }
        return cellSliderImageBinding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}