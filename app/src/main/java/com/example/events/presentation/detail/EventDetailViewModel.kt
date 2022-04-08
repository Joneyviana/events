package com.example.events.presentation.detail

import androidx.lifecycle.*
import com.example.events.constants.EventAppConstants.EMAIL
import com.example.events.constants.EventAppConstants.EVENT_ID
import com.example.events.constants.EventAppConstants.USER_NAME
import com.example.events.data.CheckIn
import com.example.events.data.EventRepository
import com.example.events.data.Resource
import com.example.events.data.local.Event
import com.example.events.data.network.RequestStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class EventDetailViewModel @Inject constructor(
    private val eventRepository: EventRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _checkIn = MutableSharedFlow<RequestStatus<ResponseBody>>()
    val checkIn = _checkIn.asSharedFlow()

    private val _eventId = MutableLiveData<String>()
    val eventDetail = _eventId.switchMap { id -> eventRepository.fetchEventDetail(id) }

    init {
        savedStateHandle.get<String>(EVENT_ID)?.let {
            fetchEventDetail(it)
        }
    }

    fun fetchEventDetail(eventId: String) {
        _eventId.value = eventId
    }

    fun checkIn(eventId: String?) = viewModelScope.launch {
        eventId?.let {
            eventRepository.checkIn(CheckIn(it, USER_NAME, EMAIL)).collect { value ->
                _checkIn.emit(value)
            }
        }
    }
}