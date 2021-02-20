package com.sedo.imageslider.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.sedo.imageslider.R
import com.sedo.imageslider.ui.adapters.ImagesRecyclerAdapter
import com.sedo.imageslider.ui.adapters.IndicatorRecyclerAdapter
import com.sedo.imageslider.ui.base.BaseBindingRecyclerViewAdapter
import com.sedo.imageslider.ui.base.views.RtlViewPager

class ImageSlider @JvmOverloads constructor(
    context: Context,
    private var attrs: AttributeSet,
    private val defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    lateinit var imagesAdapterRecyclerAdapter: ImagesRecyclerAdapter
    lateinit var indecatorRecyclerViewAdapter: IndicatorRecyclerAdapter
    lateinit var ivAttrs: TypedArray
    var indicatorItemCount: Int = 4
    var indicatorSelectedColor: Int = R.color.default_selected
    var indicatorUnSelectedColor: Int = R.color.default_unselected
    lateinit var rtlViewPager: RtlViewPager
    lateinit var recyclerView:RecyclerView
    var indecatorPosition = 0

    init {
        initAttrs()
        setUpImageAdapter(getItemsToFillViewPager())
        setUpIndicator()
    }

    private fun setUpIndicator(){
        recyclerView = RecyclerView(context)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        indecatorRecyclerViewAdapter = IndicatorRecyclerAdapter(context)
        indecatorRecyclerViewAdapter.submitItems(getItemsToFill())
        recyclerView.adapter = indecatorRecyclerViewAdapter
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.addRule(ALIGN_PARENT_BOTTOM)
        params.addRule(CENTER_IN_PARENT)
        params.bottomMargin = resources.getDimension(R.dimen._10sdp).toInt()
        recyclerView.layoutParams = params
        addView(
            recyclerView
        )
    }
    private fun getItemsToFill(): List<Boolean> {
        val list = mutableListOf<Boolean>()
        for (i in 0 until indicatorItemCount) {
            list.add(i == 0)
        }
        return list
    }

    private fun getItemsToFillViewPager(): List<String> {
        val list = mutableListOf<String>()
        for (i in 0 until indicatorItemCount) {
            list.add("i == 0")
        }
        return list
    }

    private fun setUpImageAdapter(images: List<String>) {
        rtlViewPager = RtlViewPager(context)
        imagesAdapterRecyclerAdapter = ImagesRecyclerAdapter(
            context,
            images,
            object : BaseBindingRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int, item: Any) {
//                    if (item is Image)
//                        view?.let {
//                            GalleryActivity.start(
//                                requireActivity(),
//                                imagesAdapterRecyclerAdapter.sliderArrayList,
//                                it,
//                                position
//                            )
//                        }
                }
            })
        rtlViewPager?.adapter = imagesAdapterRecyclerAdapter
        rtlViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                updateIndicator(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        addView(
            rtlViewPager,
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        )
    }

    private fun updateIndicator(position: Int) {
        indecatorRecyclerViewAdapter.items[indecatorPosition] = false
        indecatorRecyclerViewAdapter.items[position] = true
        indecatorRecyclerViewAdapter.notifyDataSetChanged()
        indecatorPosition = position
    }

    private fun initAttrs() {
        ivAttrs = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ImageSlider,
            defStyleAttr,
            0
        )
        indicatorItemCount = ivAttrs.getInteger(R.styleable.ImageSlider_indicator_count, 0)
        indicatorSelectedColor =
            ivAttrs.getResourceId(R.styleable.ImageSlider_selected_color, R.color.default_selected)
        indicatorUnSelectedColor =
            ivAttrs.getResourceId(
                R.styleable.ImageSlider_unSelected_color,
                R.color.default_unselected
            )

    }

}