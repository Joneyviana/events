package com.example.events.data.network

import com.example.events.data.Event
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsApi {
    @GET("api/events")
    suspend fun getEvents(): Response<List<Event>>

    @GET("api/events/{eventId}")
    suspend fun getEventDetail(@Path("eventId") eventId: String): Response<Event>

    @GET("api/checkin")
    suspend fun makeCheckIn(): Response<ResponseBody>
}