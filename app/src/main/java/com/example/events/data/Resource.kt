package com.example.events.data

class Resource<T> private constructor(
    val loading: Boolean = false,
    val networkFailure: Boolean = false,
    val noContent: Boolean = false,
    val isNetworkAlreadyResponse: Boolean = false,
    val loadFailure: Boolean = networkFailure && noContent,
    val contentOffline: Boolean = !noContent && networkFailure,
    val noContentServer: Boolean = noContent && isNetworkAlreadyResponse && !networkFailure,
    var data: T? = null
) {

    companion object {
        fun <T> loading(): Resource<T> {
            return Resource(loading = true)
        }

        fun <T> loaded(data: T, networkFailure: Boolean, isNetworkAlreadyResponse: Boolean): Resource<T> {
            val isEmptyList = (data as? List<*>)?.isEmpty() == true
            val hasContent = data != null && !isEmptyList
            return Resource(
                data = data,
                loading = !isNetworkAlreadyResponse && !hasContent,
                networkFailure = networkFailure,
                noContent = !hasContent,
                isNetworkAlreadyResponse = isNetworkAlreadyResponse
            )
        }
    }
}
