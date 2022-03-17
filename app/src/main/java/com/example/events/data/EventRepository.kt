package com.example.events.data

import com.example.events.data.network.EventsApi
import com.example.events.data.network.RequestStatus
import com.example.events.data.network.getNetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventsApi: EventsApi) {

    fun fetchEvents(): Flow<RequestStatus<List<Event>>> {
        return flow {

            emit(RequestStatus(loading = true))
            emit(getNetworkStatus { eventsApi.getEvents() })

        }.flowOn(Dispatchers.IO)
    }

    fun fetchEventDetail(eventId: String): Flow<RequestStatus<Event>> {
        return flow {
            emit(RequestStatus(loading = true))
            emit(getNetworkStatus { eventsApi.getEventDetail(eventId) })

        }.flowOn(Dispatchers.IO)
    }

    fun checkIn(checkIn: CheckIn): Flow<RequestStatus<ResponseBody>> {
        return flow {
            emit(RequestStatus(loading = true))
            emit(getNetworkStatus { eventsApi.makeCheckIn(checkIn) })

        }.flowOn(Dispatchers.IO)
    }
}