package com.example.events.data

import org.junit.Assert.assertEquals
import org.junit.Test

class ResourceTest {

    @Test
    fun `Should return correct attrs when success load`() {
        val resource = Resource.loaded("text", networkFailure = false, isNetworkAlreadyResponse = true)
        checkCaseWithContent(resource)
        assertEquals(false, resource.loading)

        val resourceOne = Resource.loaded(null, networkFailure = false, isNetworkAlreadyResponse = true)
        assertEquals(true, resourceOne.noContentServer)
        assertEquals(false, resourceOne.loading)
        assertEquals(true, resourceOne.noContent)
        assertEquals(false, resourceOne.contentOffline)
        assertEquals(false, resourceOne.loadFailure)
    }

    @Test
    fun `Should return correct attrs when offline data`() {
        val resource = Resource.loaded("text", networkFailure = true, isNetworkAlreadyResponse = true)
        assertEquals(true, resource.contentOffline)
        assertEquals(false, resource.loadFailure)
        assertEquals(false, resource.loading)
        assertEquals(false, resource.noContent)
        assertEquals(false, resource.noContentServer)
    }
    @Test
    fun `Should return correct attrs when network failed and no offline data`() {
        val resourceOne = Resource.loaded(null, networkFailure = true, isNetworkAlreadyResponse = true)
        checkOnFailureAndNoContent(resourceOne)

        val resourceTwo =
            Resource.loaded(mutableListOf<String>(), networkFailure = true, isNetworkAlreadyResponse = true)
        checkOnFailureAndNoContent(resourceTwo)
    }

    @Test
    fun `Should return correct attrs when loading data`() {
        val resource = Resource.loading<String>()
        checkCaseWithContent(resource)
        assertEquals(true, resource.loading)
    }

    @Test
    fun `Should return correct on database load before server response`() {
        val resourceOne = Resource.loaded("text", networkFailure = false, isNetworkAlreadyResponse = false)
        checkCaseWithContent(resourceOne)
        assertEquals(false, resourceOne.loading)

        val resourceTwo = Resource.loaded(null, networkFailure = false, isNetworkAlreadyResponse = false)
        checkOnNoFailed(resourceTwo)
        assertEquals(true, resourceTwo.noContent)
        assertEquals(true, resourceTwo.loading)
    }

    private fun <T> checkOnFailureAndNoContent(resource: Resource<T>) {
        assertEquals(false, resource.contentOffline)
        assertEquals(true, resource.loadFailure)
        assertEquals(false, resource.loading)
        assertEquals(true, resource.noContent)
        assertEquals(false, resource.noContentServer)
    }

    private fun <T> checkOnNoFailed(resource: Resource<T>) {
        assertEquals(false, resource.contentOffline)
        assertEquals(false, resource.loadFailure)
        assertEquals(false, resource.noContentServer)
    }

    private fun <T> checkCaseWithContent(resource: Resource<T>) {
        checkOnNoFailed(resource)
        assertEquals(false, resource.noContent)
    }
}
