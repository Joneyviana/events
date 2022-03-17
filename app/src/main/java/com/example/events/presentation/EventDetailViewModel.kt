package com.example.events.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.events.constants.EventAppConstants.EMAIL
import com.example.events.constants.EventAppConstants.USER_NAME
import com.example.events.data.CheckIn
import com.example.events.data.Event
import com.example.events.data.EventRepository
import com.example.events.data.network.RequestStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class EventDetailViewModel : ViewModel() {
    private val eventRepository = EventRepository()

    private val _eventDetail: MutableLiveData<RequestStatus<Event>> = MutableLiveData()
    val eventDetail: LiveData<RequestStatus<Event>> = _eventDetail

    private val _checkIn: MutableLiveData<RequestStatus<ResponseBody>> = MutableLiveData()
    val checkIn: LiveData<RequestStatus<ResponseBody>> = _checkIn

    fun fetchEventDetail(eventId: String) = viewModelScope.launch {
        eventRepository.fetchEventDetail(eventId).collect { value ->
            _eventDetail.value = value
        }
    }

    fun checkIn(eventId: String) = viewModelScope.launch {
        eventRepository.checkIn(CheckIn(eventId, USER_NAME, EMAIL)).collect { value ->
            _checkIn.value = value
        }
    }
}