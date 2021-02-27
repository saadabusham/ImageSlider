package com.sedo.imageslider.ui.base.binding

import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView


@BindingAdapter(value = ["indicator_height", "indicator_width"], requireAll = true)
fun MaterialCardView.setLayoutParams(indicator_height: Float, indicator_width: Float) {
    val layoutParams = this.layoutParams
    layoutParams.height = indicator_height.toInt()
    layoutParams.width = indicator_width.toInt()
    this.layoutParams = layoutParams
    this.radius = indicator_height/2
}

@BindingAdapter("cvIsChecked")
fun MaterialCardView?.setIsChecked(isChecked: Boolean) {
    this?.isChecked = isChecked
}