package com.example.events.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.events.data.Event
import com.example.events.data.EventRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {
    private val eventRepository = EventRepository()
    private val _events: MutableLiveData<List<Event>> = MutableLiveData()
    val events: LiveData<List<Event>> = _events

    init {
        fetchEvents()
    }

    private fun fetchEvents() = viewModelScope.launch {
        eventRepository.fetchEvents().collect { value ->
            _events.value = value
        }
    }

}