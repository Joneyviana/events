package com.example.events.fakedata

import com.example.events.data.local.Event
import okhttp3.ResponseBody
import retrofit2.Response

object FakeDataResponse {
    private var failure = false
    private var isEmpty = false
    private val events = mutableListOf(
        Event(
            "0",
            "title 1",
            null,
            "https://en.wikipedia.org/wiki/Image#/media/File:Image_created_with_a_mobile_phone.png",
            latitude = -30.0392981,
            longitude = -51.2148497,
            price = "2.74"
        ),
        Event(
            "1",
            "title 2",
            "description 2",
            "urlInvalid"
        ),
        Event(
            "2",
            "title 3",
            "description 3",
            null
        ),
        Event(
            "3",
            "title 4",
            "description 4",
            ""
        )

    )

    fun getEvents(): Response<List<Event>> {
        if (isEmpty) {
            return Response.success(mutableListOf())
        }
        return checkFailureAnReturn(events)
    }

    fun getEventDetail(eventId: String): Response<Event> {
        val event = events.find { it.id == eventId }
        return event?.run {
            checkFailureAnReturn(event)
        } ?: Response.error(400, ResponseBody.create(null, "failed"))
    }

    fun checkIn(): Response<ResponseBody> {
        return checkFailureAnReturn(ResponseBody.create(null, ""))
    }

    fun setFailureAsResponse() {
        failure = true
    }

    fun removeFailureAsResponse() {
        failure = false
    }

    fun setEmptyAsResponse() {
        isEmpty = true
    }

    fun removeEmptyAsResponse() {
        isEmpty = false
    }

    private fun <T> checkFailureAnReturn(type: T): Response<T> {
        return if (!failure) {
            Response.success(type)
        } else {
            Response.error(400, ResponseBody.create(null, "failed"))
        }
    }
}