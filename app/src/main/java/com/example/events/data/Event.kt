package com.example.events.data

data class Event(
    val id: String,
    val title: String,
    val description: String = "",
    val image:String? = null,
    val longitude: Int? = null,
    val latitude: Int? = null,
    val price: String = "",
    val date: Long? = null,
    val people: List<CheckIn> = listOf()
)