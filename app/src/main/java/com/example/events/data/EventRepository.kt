package com.example.events.data

import com.example.events.data.network.RequestStatus
import com.example.events.data.network.RetrofitConfig
import com.example.events.data.network.getNetworkStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import retrofit2.Response

class EventRepository {

    fun fetchEvents(): Flow<RequestStatus<List<Event>>> {
        return flow {

            emit(RequestStatus(loading = true))
            emit(getNetworkStatus { RetrofitConfig.eventsApi.getEvents() })

        }.flowOn(Dispatchers.IO)
    }

    fun fetchEventDetail(eventId: String): Flow<RequestStatus<Event>> {
        return flow {
            emit(RequestStatus(loading = true))
            emit(getNetworkStatus { RetrofitConfig.eventsApi.getEventDetail(eventId) })

        }.flowOn(Dispatchers.IO)
    }

    fun checkIn(checkIn: CheckIn): Flow<RequestStatus<ResponseBody>> {
        return flow {
            emit(RequestStatus(loading = true))
            emit(getNetworkStatus { RetrofitConfig.eventsApi.makeCheckIn(checkIn) })

        }.flowOn(Dispatchers.IO)
    }
}