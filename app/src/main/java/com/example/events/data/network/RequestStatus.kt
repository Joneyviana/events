package com.example.events.data.network

class RequestStatus<T> private constructor(
    val loading: Boolean = false,
    val failed: Boolean = false,
    var data: T? = null
) {

    constructor(data: T) : this() {
        this.data = data
    }

    companion object {
        fun <T> loading(): RequestStatus<T> {
            return RequestStatus(loading = true)
        }

        fun <T> failed(): RequestStatus<T> {
            return RequestStatus(failed = true)
        }
    }
}
