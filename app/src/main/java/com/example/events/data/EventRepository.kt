package com.example.events.data

import com.example.events.data.network.RetrofitConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EventRepository {

    fun fetchEvents(): Flow<List<Event>> {
        return flow {
            RetrofitConfig.eventsApi.getEvents().body()?.let { emit(it) }
        }.flowOn(Dispatchers.IO)
    }

    fun fetchEventDetail(eventId: String): Flow<Event> {
        return flow {
            RetrofitConfig.eventsApi.getEventDetail(eventId).body()?.let { emit(it) }
        }.flowOn(Dispatchers.IO)
    }
}