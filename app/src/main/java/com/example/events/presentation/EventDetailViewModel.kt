package com.example.events.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.events.data.Event
import com.example.events.data.EventRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventDetailViewModel : ViewModel() {
    private val eventRepository = EventRepository()
    private val _eventDetail: MutableLiveData<Event> = MutableLiveData()
    val eventDetail: LiveData<Event> = _eventDetail


    fun fetchEventDetail(eventId: String) = viewModelScope.launch {
        eventRepository.fetchEventDetail(eventId).collect { value ->
            _eventDetail.value = value
        }
    }
}