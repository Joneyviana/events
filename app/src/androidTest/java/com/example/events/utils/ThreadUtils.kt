package com.example.events.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun executeOnUI(function: () -> Unit) = runBlocking {
    withContext(Dispatchers.Main) {
        function.invoke()
    }
}
