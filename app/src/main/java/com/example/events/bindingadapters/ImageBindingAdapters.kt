package com.example.events.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.events.R
import java.net.URLEncoder


@BindingAdapter("imageURL")
fun AppCompatImageView.loadImage(url: String) {
    if (url.isNotEmpty()) {
        Glide.with(this.context).load(url).centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .into(this)
    } else {
        this.setImageResource(R.drawable.ic_placeholder)
    }
}