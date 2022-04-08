package com.example.events.data.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.events.data.CheckIn

@Entity(tableName = "event")
data class Event(
    @PrimaryKey
    var id: String,
    var title: String? = null,
    var description: String? = null,
    var image: String? = null,
    var longitude: Double? = null,
    var latitude: Double? = null,
    var price: String = "",
    var date: Long? = null,
    @Ignore
    var people: List<CheckIn> = listOf()
) {
    constructor() : this(id = "")

    fun getSharedText(): String {
        return "$title \n\n $description \n\n  $image"
    }
}