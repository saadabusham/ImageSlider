package com.sedo.imageslider.ui.base.binding

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sedo.imageslider.R
import com.sedo.imageslider.util.extenstion.px
import java.io.File

@BindingAdapter("ivSetSrcImageFromResources")
fun ImageView.setImageFromResources(@DrawableRes imageRes: Int) {
    setImageResource(imageRes)
}

@BindingAdapter(
    value = [
        "ivImageUrl",
        "ivImagePlaceholder",
        "ivImageErrorPlaceholder",
        "ivImageIsCircle",
        "ivImageIsRoundedCorners",
        "ivImageRoundedRadius"
    ],
    requireAll = false
)
fun ImageView.setImageFromUrl(
    imageUrl: String?,
    @DrawableRes imagePlaceholder: Int? = null,
    @DrawableRes imageErrorPlaceholder: Int? = null,
    imageIsCircle: Boolean = false,
    imageIsRoundedCorners: Boolean = false,
    roundingRadius: Int? = null
) {
    if (imageUrl.isNullOrEmpty()) {
        setImageResource(imageErrorPlaceholder ?: R.drawable.ic_default_image_place_holder)
        return
    }

    Glide.with(context)
        .load(getLoadingUrl(imageUrl))
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
        })
        .placeholder(imagePlaceholder ?: R.drawable.ic_default_image_place_holder)
        .apply(setUpRequestOptions(imageIsCircle, imageIsRoundedCorners, roundingRadius ?: 10))
        .error(imageErrorPlaceholder ?: R.drawable.ic_default_image_place_holder)
        .into(this)
}

fun setUpRequestOptions(
    imageIsCircle: Boolean,
    imageIsRoundedCorners: Boolean,
    roundingRadius: Int
): BaseRequestOptions<*> =
    when {
        imageIsCircle -> RequestOptions.circleCropTransform()
        imageIsRoundedCorners -> RequestOptions().transform(
            CenterCrop(), RoundedCorners(roundingRadius.px())
        )
        else -> RequestOptions.noTransformation()
    }


fun getLoadingUrl(imageUrl: String): Any {

    if (imageUrl.contains("storage")) {
        return Uri.fromFile(File(imageUrl))
    } else if (imageUrl.startsWith("content", true)) {
        return Uri.parse(imageUrl)
    }
    return imageUrl
}

@BindingAdapter("imageRec")
fun ImageView?.setImageFromRec(
    @DrawableRes imageRes: Int
) {
    this?.setImageResource(imageRes)
}

