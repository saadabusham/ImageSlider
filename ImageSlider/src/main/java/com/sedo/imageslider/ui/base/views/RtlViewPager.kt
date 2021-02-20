package com.sedo.imageslider.ui.base.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager

class RtlViewPager : ViewPager {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    override fun onRtlPropertiesChanged(layoutDirection: Int) {
        super.onRtlPropertiesChanged(layoutDirection)
        if (layoutDirection == LAYOUT_DIRECTION_RTL) {
            rotationY = 180f
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onViewAdded(child: View) {
        if (layoutDirection == LAYOUT_DIRECTION_RTL) {
            child.rotationY = 180f
        }
        super.onViewAdded(child)
    }
}