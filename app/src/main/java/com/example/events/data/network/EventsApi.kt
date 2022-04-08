package com.example.events.data.network

import com.example.events.data.CheckIn
import com.example.events.data.local.Event
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsApi {
    @GET("api/events")
    suspend fun getEvents(): Response<List<Event>>

    @GET("api/events/{eventId}")
    suspend fun getEventDetail(@Path("eventId") eventId: String): Response<Event>

    @POST("api/checkin")
    suspend fun makeCheckIn(@Body checkIn: CheckIn): Response<ResponseBody>
}