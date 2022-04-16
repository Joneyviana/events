package com.example.events.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter(value = ["timestamp", "patternDate"], requireAll = false)
fun TextView.applyDate(timestamp: Long?, patternDate: String?) {
    timestamp?.let {
        val dateFormat = SimpleDateFormat(patternDate, Locale.getDefault())
        this.text = dateFormat.format(timestamp)
    }
}
