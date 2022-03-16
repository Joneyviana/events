package com.example.events.data.network

import retrofit2.Response

data class RequestStatus<T>(
    val loading: Boolean = false,
    val failed: Boolean = false,
    val success: T? = null
) {

    companion object {
        fun <T> checkStatus(response: Response<T>): RequestStatus<T> {
            return if (response.isSuccessful) {
                RequestStatus(success = response.body())
            } else {
                RequestStatus(failed = true)
            }
        }
    }
}