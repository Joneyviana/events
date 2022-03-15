package com.example.events.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitConfig {
    private const val BASE_API = "https://5f5a8f24d44d640016169133.mockapi.io/"

    val eventsApi = Retrofit.Builder()
        .baseUrl(BASE_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(EventsApi::class.java)
}
