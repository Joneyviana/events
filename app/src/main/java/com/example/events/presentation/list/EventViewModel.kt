package com.example.events.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.events.data.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val eventRepository: EventRepository) : ViewModel() {
    private val shouldReload = MutableLiveData<Boolean>()
    val events = shouldReload.switchMap { eventRepository.fetchEvents() }

    init {
        loadEvents()
    }

    fun loadEvents() {
        shouldReload.value = true
    }
}
