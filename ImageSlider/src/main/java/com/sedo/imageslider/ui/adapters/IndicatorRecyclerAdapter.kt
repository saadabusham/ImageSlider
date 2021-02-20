package com.sedo.imageslider.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedo.imageslider.R
import com.sedo.imageslider.databinding.RowIndecatorBinding
import com.sedo.imageslider.ui.base.BaseBindingRecyclerViewAdapter
import com.sedo.imageslider.ui.base.BaseViewHolder

class IndicatorRecyclerAdapter(
    context: Context
) : BaseBindingRecyclerViewAdapter<Boolean>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            RowIndecatorBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(items[position])
        }
    }

    inner class ViewHolder(private val binding: RowIndecatorBinding) :
        BaseViewHolder<Boolean>(binding.root) {

        override fun bind(item: Boolean) {
            if (item) {
                binding.imgDotImage.setCardBackgroundColor(context.resources.getColor(R.color.default_selected))
            } else {
                binding.imgDotImage.setCardBackgroundColor(context.resources.getColor(R.color.default_unselected))
            }
        }
    }
}