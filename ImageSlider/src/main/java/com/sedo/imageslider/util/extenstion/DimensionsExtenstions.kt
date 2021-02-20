package com.sedo.imageslider.util.extenstion

import android.content.res.Resources


fun Int.dp() : Int{
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.px() : Int{
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}