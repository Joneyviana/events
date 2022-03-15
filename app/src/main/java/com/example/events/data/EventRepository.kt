package com.example.events.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRepository {

    suspend fun fetchEvents() : Flow<List<Event>> {
        return flow {
            emit(listOf(
                Event("1", title = "Title 1", price = "2.29"),
                Event("1",title = "Title 2", price = "5.29")))
        }
    }
}