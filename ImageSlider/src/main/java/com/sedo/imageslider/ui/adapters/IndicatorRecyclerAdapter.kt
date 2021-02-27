package com.sedo.imageslider.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedo.imageslider.R
import com.sedo.imageslider.databinding.RowIndecatorBinding
import com.sedo.imageslider.indecator.Indicator
import com.sedo.imageslider.ui.base.BaseBindingRecyclerViewAdapter
import com.sedo.imageslider.ui.base.BaseViewHolder

class IndicatorRecyclerAdapter(
    context: Context,
    private val selectedColor:Int,
    private val unSelectedColor:Int
) : BaseBindingRecyclerViewAdapter<Indicator>(context) {

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
        BaseViewHolder<Indicator>(binding.root) {

        override fun bind(item: Indicator) {
            binding.data = item
            if (item.selected) {
                binding.imgDotImage.setCardBackgroundColor(context.resources.getColor(selectedColor))
            } else {
                binding.imgDotImage.setCardBackgroundColor(context.resources.getColor(unSelectedColor))
            }
        }
    }
}