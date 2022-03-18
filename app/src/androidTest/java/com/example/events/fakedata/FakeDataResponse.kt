package com.example.events.fakedata

import com.example.events.data.Event
import okhttp3.ResponseBody
import retrofit2.Response

object FakeDataResponse {
    private var failure = false
    private val events = mutableListOf(
        Event(
            "0", "title 1", "description 1",
            "https://en.wikipedia.org/wiki/Image#/media/File:Image_created_with_a_mobile_phone.png"
        ),
        Event(
            "1", "title 2", "description 2",
            "https://en.wikipedia.org/wiki/Image#/media/File:Image_created_with_a_mobile_phone.png"
        ),
        Event(
            "2", "title 3", "description 3",
            "https://en.wikipedia.org/wiki/Image#/media/File:Image_created_with_a_mobile_phone.png"
        )
    )

    fun getEvents(): Response<List<Event>> {
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

    fun setFailure() {
        failure = true
    }

    fun removeFailure() {
        failure = false
    }

    private fun <T> checkFailureAnReturn(type: T): Response<T> {
        return if (!failure) {
            Response.success(type)
        } else {
            Response.error(400, ResponseBody.create(null, "failed"))
        }
    }


}