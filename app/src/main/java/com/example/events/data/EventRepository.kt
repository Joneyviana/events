package com.example.events.data

import com.example.events.data.local.Event
import com.example.events.data.local.EventDao
import com.example.events.data.network.EventsApi
import com.example.events.data.network.RequestStatus
import com.example.events.data.network.RequestStatus.Companion.loading
import com.example.events.data.network.getNetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventsApi: EventsApi, private val eventDao: EventDao) {

    fun fetchEvents() = loadResource(
        databaseQuery = { eventDao.getAllEvents() },
        networkCall = { getNetworkStatus { eventsApi.getEvents() } },
        saveCallResult = { eventDao.insertAll(it) })

    fun fetchEventDetail(eventId: String) = loadResource(
        databaseQuery = { eventDao.getEventById(eventId) },
        networkCall = { getNetworkStatus { eventsApi.getEventDetail(eventId) } },
        saveCallResult = { eventDao.insert(it) })

    fun checkIn(checkIn: CheckIn): Flow<RequestStatus<ResponseBody>> {
        return flow {
            emit(loading())
            emit(getNetworkStatus { eventsApi.makeCheckIn(checkIn) })

        }.flowOn(Dispatchers.IO)
    }
}