package com.example.events.data

import com.example.events.data.network.RequestStatus
import com.example.events.data.network.RetrofitConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EventRepository {

    fun fetchEvents(): Flow<RequestStatus<List<Event>>> {
        return flow {

            emit(RequestStatus(loading = true))
            emit(RequestStatus.checkStatus(RetrofitConfig.eventsApi.getEvents()))

        }.flowOn(Dispatchers.IO)
    }

    fun fetchEventDetail(eventId: String): Flow<RequestStatus<Event>> {
        return flow {

            emit(RequestStatus(loading = true))
            emit(RequestStatus.checkStatus(RetrofitConfig.eventsApi.getEventDetail(eventId)))

        }.flowOn(Dispatchers.IO)
    }
}