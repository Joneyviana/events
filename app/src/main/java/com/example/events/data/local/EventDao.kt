package com.example.events.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<Event>)

    @Query("SELECT * FROM event ORDER BY id ASC")
    fun getAllEvents(): LiveData<List<Event>>

    @Query("SELECT * FROM event where id = :id")
    fun getEventById(id: String): LiveData<Event>

    @Query("DELETE FROM event")
    fun deleteAll()
}