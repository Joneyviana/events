package com.example.events.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.events.data.network.RequestStatus
import kotlinx.coroutines.Dispatchers

fun <T, A> loadResource(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> RequestStatus<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val databaseReturn = databaseQuery.invoke()
        var isNetworkAlreadyResponse = false
        var networkFailure = false
        emitSource(databaseReturn.map { Resource.loaded(it, networkFailure, isNetworkAlreadyResponse) })

        val responseStatus = networkCall.invoke()
        if (!responseStatus.failed) {
            isNetworkAlreadyResponse = true
            saveCallResult(responseStatus.data!!)
        } else {
            networkFailure = true
            isNetworkAlreadyResponse = true
            emitSource(databaseReturn.map { Resource.loaded(it, networkFailure, isNetworkAlreadyResponse) })
        }
    }
