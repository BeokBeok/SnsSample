package com.beok.shared.binding.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bind_imageView_srcForGlide")
fun srcForGlide(imageView: ImageView, url: String?) {
    if (url == null) return
    Glide.with(imageView)
        .load(url)
        .into(imageView)
}
