package com.sedo.imageslider.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.sedo.imageslider.databinding.RowImageBinding
import com.sedo.imageslider.ui.base.BaseBindingRecyclerViewAdapter

class ImagesRecyclerAdapter(
    private val mContext: Context,
    val sliderArrayList: List<String>,
    private val itemClickListeners: BaseBindingRecyclerViewAdapter.OnItemClickListener? = null
) : PagerAdapter() {

    override fun getCount(): Int {
        return sliderArrayList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = (mContext as Activity).layoutInflater
        val cellSliderImageBinding = RowImageBinding
            .inflate(inflater, container, false)
        cellSliderImageBinding.image = sliderArrayList[position]
        container.addView(cellSliderImageBinding.root, 0)
        cellSliderImageBinding?.root?.setOnClickListener {
            itemClickListeners?.onItemClick(
                cellSliderImageBinding?.ivImage,
                position,
                sliderArrayList[position]
            )
        }
        return cellSliderImageBinding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}