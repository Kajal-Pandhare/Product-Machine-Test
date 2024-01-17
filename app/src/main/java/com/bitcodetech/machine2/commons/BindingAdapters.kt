package com.bitcodetech.machine2.commons

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bitcodetech.machine2.R
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun setImageUrlToImageView(imageView: ImageView,imageUrl : String) {
    Glide.with(imageView)
        .load(imageUrl)
        .error(R.mipmap.ic_launcher)
        .into(imageView)
}