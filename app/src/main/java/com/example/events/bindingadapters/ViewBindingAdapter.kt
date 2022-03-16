package com.example.events.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibilityDependOnNullable")
fun View.setVisibility(element: Any?) {
    this.visibility = if (element == null) {
        View.GONE
    } else {
        View.VISIBLE
    }

}