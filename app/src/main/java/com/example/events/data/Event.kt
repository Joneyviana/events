package com.example.events.data

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val image:String,
    val longitude: Int,
    val latitude: Int,
    val price: String,
    val date: Long,
    val people: List<CheckIn>
)