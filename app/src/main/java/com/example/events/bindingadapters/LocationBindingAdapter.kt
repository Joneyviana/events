package com.example.events.bindingadapters

import android.location.Geocoder
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*


@BindingAdapter(value = ["latitude", "longitude"], requireAll = false)
fun TextView.setAddress(latitude: Double?, longitude: Double?) {
    if (latitude != null && longitude != null) {
        val address = Geocoder(this.context, Locale.getDefault()).getFromLocation(latitude, longitude, 1)
        var textAddress = "Address not found"
        if (address.isNotEmpty()) {
            textAddress = address[0].getAddressLine(0)
        }
        this.text = textAddress
    }
}