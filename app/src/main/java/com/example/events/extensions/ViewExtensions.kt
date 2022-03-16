package com.example.events.extensions

import android.view.View

@JvmOverloads
fun Boolean.toVisibility(isInvisible: Boolean = false): Int {
    return when {
        this -> {
            View.VISIBLE
        }
        isInvisible -> {
            View.INVISIBLE
        }
        else -> {
            View.GONE
        }
    }
}