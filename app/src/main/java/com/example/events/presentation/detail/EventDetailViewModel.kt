package com.example.events.presentation.detail

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(private val eventRepository: EventRepository) : ViewModel() {

    private val _eventDetail: MutableLiveData<RequestStatus<Event>> = MutableLiveData()
    val eventDetail: LiveData<RequestStatus<Event>> = _eventDetail

    private val _checkIn: MutableLiveData<RequestStatus<ResponseBody>> = MutableLiveData()
    val checkIn: LiveData<RequestStatus<ResponseBody>> = _checkIn

    fun fetchEventDetail(eventId: String) = viewModelScope.launch {
        eventRepository.fetchEventDetail(eventId).collect { value ->
            _eventDetail.value = value
        }
    }

    fun checkIn(eventId: String?) = viewModelScope.launch {
        eventId?.let {
            eventRepository.checkIn(CheckIn(it, USER_NAME, EMAIL)).collect { value ->
                _checkIn.value = value
            }
        }

    }
}