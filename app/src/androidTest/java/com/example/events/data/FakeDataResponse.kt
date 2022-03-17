package com.example.events.data

import android.provider.CalendarContract
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
            "0", "title 3", "description 3",
            "https://en.wikipedia.org/wiki/Image#/media/File:Image_created_with_a_mobile_phone.png"
        )
    )

    fun getEvents(): Response<List<Event>> {
        return if (!failure) {
            Response.success(events)
        } else {
            Response.error(400, ResponseBody.create(null, "failed"))
        }
    }

    fun setFailure() {
        failure = true
    }

    fun removeFailure() {
        failure = false
    }


}