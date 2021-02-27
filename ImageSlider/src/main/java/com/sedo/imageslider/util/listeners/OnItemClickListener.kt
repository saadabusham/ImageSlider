package com.sedo.imageslider.util.listeners

import android.view.View


interface OnItemClickListener {
    fun onItemClick(view: View?, position: Int, item: Any)
    fun onItemLongClick(view: View?, position: Int, item: Any){}
}