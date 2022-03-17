package com.example.events.data

import com.example.events.data.network.EventsApi
import okhttp3.ResponseBody
import retrofit2.Response

class FakeEventApi : EventsApi {
    override suspend fun getEvents(): Response<List<Event>> {
        return FakeDataResponse.getEvents()
    }

    override suspend fun getEventDetail(eventId: String): Response<Event> {
        TODO("Not yet implemented")
    }

    override suspend fun makeCheckIn(checkIn: CheckIn): Response<ResponseBody> {
        TODO("Not yet implemented")
    }
}