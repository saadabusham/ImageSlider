package com.sedo.imageslider.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.sedo.imageslider.R
import com.sedo.imageslider.indecator.Indicator
import com.sedo.imageslider.ui.adapters.ImagesRecyclerAdapter
import com.sedo.imageslider.ui.adapters.IndicatorRecyclerAdapter
import com.sedo.imageslider.ui.base.views.RtlViewPager
import com.sedo.imageslider.util.listeners.OnItemClickListener

class ImageSlider @JvmOverloads constructor(
    context: Context,
    private var attrs: AttributeSet,
    private val defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var imagesAdapterRecyclerAdapter: ImagesRecyclerAdapter? = null
    private lateinit var indicatorRecyclerViewAdapter: IndicatorRecyclerAdapter
    private lateinit var ivAttrs: TypedArray
    private var indicatorItemCount: Int = 0
    private var indicatorSelectedColor: Int = R.color.default_selected
    private var indicatorUnSelectedColor: Int = R.color.default_unselected
    private var indicatorHeightSize: Int = R.dimen._8sdp
    private var indicatorWidthSize: Int = R.dimen._8sdp
    private lateinit var rtlViewPager: RtlViewPager
    private lateinit var recyclerView: RecyclerView
    private var indicatorPosition = 0

    var itemClickListener: OnItemClickListener? = null
        set(value) {
            imagesAdapterRecyclerAdapter?.itemClickListener = value
            field = value
        }

    var sliderArrayListString: List<String>? = null
        set(value) {
            if (value != null) {
                sliderArrayListResource = null
                setUpImageAdapter(imagesStrings = value)
            }
            field = value
        }
    var sliderArrayListResource: List<Int>? = null
        set(value) {
            if (value != null) {
                sliderArrayListString = null
                setUpImageAdapter(imagesResource = value)
            }
            field = value
        }

    init {
        initAttrs()
    }

    private fun setUpIndicator() {
        recyclerView = RecyclerView(context)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        indicatorRecyclerViewAdapter =
            IndicatorRecyclerAdapter(context, indicatorSelectedColor, indicatorUnSelectedColor)
        indicatorRecyclerViewAdapter.submitItems(getItemsToFill())
        recyclerView.adapter = indicatorRecyclerViewAdapter
        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.addRule(ALIGN_PARENT_BOTTOM)
        params.addRule(CENTER_IN_PARENT)
        params.bottomMargin = resources.getDimension(R.dimen._10sdp).toInt()
        recyclerView.layoutParams = params
        addView(
            recyclerView
        )
    }

    private fun getItemsToFill(): List<Indicator> {
        val list = mutableListOf<Indicator>()
        for (i in 0 until indicatorItemCount) {
            list.add(
                Indicator(
                    i == 0,
                    resources.getDimension(indicatorWidthSize).toInt(),
                    resources.getDimension(indicatorHeightSize).toInt()
                )
            )
        }
        return list
    }

//    private fun getItemsToFillViewPager(): List<String> {
//        val list = mutableListOf<String>()
//        for (i in 0 until indicatorItemCount) {
//            list.add("i == 0")
//        }
//        return list
//    }

    private fun setUpImageAdapter(
        imagesResource: List<Int>? = null,
        imagesStrings: List<String>? = null
    ) {
        rtlViewPager = RtlViewPager(context)
        imagesAdapterRecyclerAdapter = ImagesRecyclerAdapter(
            context,
            imagesResource != null,
            imagesStrings,
            imagesResource
        )
        rtlViewPager.adapter = imagesAdapterRecyclerAdapter
        rtlViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
        indicatorItemCount = imagesResource?.size ?: (imagesStrings?.size ?: 0)
        setUpIndicator()
    }

    private fun updateIndicator(position: Int) {
        indicatorRecyclerViewAdapter?.items[indicatorPosition].selected = false
        indicatorRecyclerViewAdapter?.items[position].selected = true
        indicatorRecyclerViewAdapter?.notifyDataSetChanged()
        indicatorPosition = position
    }

    private fun initAttrs() {
        ivAttrs = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ImageSlider,
            defStyleAttr,
            0
        )
//        indicatorItemCount = ivAttrs.getInteger(R.styleable.ImageSlider_indicator_count, 0)
        indicatorSelectedColor =
            ivAttrs.getResourceId(R.styleable.ImageSlider_selected_color, R.color.default_selected)
        indicatorUnSelectedColor =
            ivAttrs.getResourceId(
                R.styleable.ImageSlider_unSelected_color,
                R.color.default_unselected
            )
        indicatorHeightSize =
            ivAttrs.getResourceId(
                R.styleable.ImageSlider_indicator_height_size,
                R.dimen._8sdp
            )
        indicatorWidthSize =
            ivAttrs.getResourceId(
                R.styleable.ImageSlider_indicator_width_size,
                R.dimen._8sdp
            )
    }

}