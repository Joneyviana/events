package com.example.events.data.network

import com.example.events.data.Event
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface EventsApi {
    @GET("api/events")
    suspend fun getEvents(): Response<List<Event>>

    @GET("api/events/{id}")
    suspend fun getEventDetail(): Response<Event>

    @GET("api/checkin")
    suspend fun makeCheckIn(): Response<ResponseBody>
}