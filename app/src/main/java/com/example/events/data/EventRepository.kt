package com.example.events.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRepository {

    fun fetchEvents() : Flow<List<Event>> {
        return flow {
            emit(listOf())
        }
    }
}