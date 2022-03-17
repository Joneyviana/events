package com.example.events.data.network

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
        RequestStatus(failed = true)
    }
}

private fun <T> getStatusResponse(response: Response<T>): RequestStatus<T> {
    return if (response.isSuccessful) {
        RequestStatus(success = response.body())
    } else {
        RequestStatus(failed = true)
    }
}