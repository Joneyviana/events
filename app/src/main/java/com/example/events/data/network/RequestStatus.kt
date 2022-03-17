package com.example.events.data.network

data class RequestStatus<T>(
    val loading: Boolean = false,
    val failed: Boolean = false,
    val success: T? = null
)