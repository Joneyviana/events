package com.example.events.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.events.data.Event
import com.example.events.data.EventRepository
import com.example.events.data.network.RequestStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val eventRepository: EventRepository) : ViewModel()  {
    private val _events: MutableLiveData<RequestStatus<List<Event>>> = MutableLiveData()
    val events: LiveData<RequestStatus<List<Event>>>  = _events

    init {
        fetchEvents()
    }

    fun fetchEvents() = viewModelScope.launch {
        eventRepository.fetchEvents().collect { value ->
            _events.value = value
        }
    }

}