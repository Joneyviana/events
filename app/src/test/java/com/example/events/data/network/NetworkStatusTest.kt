package com.example.events.data.network

import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Response

class NetworkStatusTest {

    @Test
    fun shouldReturnRequestSuccess(): Unit = runBlocking {
        val result = getNetworkStatus { Response.success(true) }
        assertTrue(result.data == true)
        assertFalse(result.failed)
    }

    @Test
    fun shouldReturnRequestFailed(): Unit = runBlocking {
        val result = getNetworkStatus {
            Response.error<String>(400, ResponseBody.create(null, "failed"))
        }

        assertNull(result.data)
        assertTrue(result.failed)
    }

    @Test
    fun shouldReturnRequestFailedOnException(): Unit = runBlocking {
        val result = getNetworkStatus<String> { throw Exception("failed") }
        assertNull(result.data)
        assertTrue(result.failed)
    }
}
