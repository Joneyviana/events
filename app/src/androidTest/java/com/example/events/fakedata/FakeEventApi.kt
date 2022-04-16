package com.example.events.fakedata

import com.example.events.data.CheckIn
import com.example.events.data.local.Event
import com.example.events.data.network.EventsApi
import okhttp3.ResponseBody
import retrofit2.Response

class FakeEventApi : EventsApi {
    override suspend fun getEvents(): Response<List<Event>> {
        return FakeDataResponse.getEvents()
    }

    override suspend fun getEventDetail(eventId: String): Response<Event> {
        return FakeDataResponse.getEventDetail(eventId)
    }

    override suspend fun makeCheckIn(checkIn: CheckIn): Response<ResponseBody> {
        return FakeDataResponse.checkIn()
    }
}
