package com.example.events.data.network

import android.util.Log
import com.example.events.data.network.RequestStatus.Companion.failed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> getNetworkStatus(responseCall: suspend () -> Response<T>): RequestStatus<T> {
    return try {
        coroutineScope {
            withContext(Dispatchers.IO) {
                getStatusResponse(responseCall())
            }
        }
    } catch (exception: Exception) {
        failed()
    }
}

private fun <T> getStatusResponse(response: Response<T>): RequestStatus<T> {
    return if (response.isSuccessful) {
        response.body()?.let { RequestStatus(data = it) } ?: failed()
    } else {
        Log.i("failed message", "message: ${response.errorBody()?.string()}")
        failed()
    }
}
