package com.example.events.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.events.R
import com.example.events.data.EventRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventsFragmentTest {

    @Test
    fun shouldLoadEvents(): Unit = runBlocking {
        val eventRepository = EventRepository()
        val events = eventRepository.fetchEvents().firstOrNull()
        assertNotNull(events)
        events?.forEach {
            onView(withId(R.id.recycler_view_events))
                .perform(
                    RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>
                        (hasDescendant(withText(it.title)), click())
                );
        }
    }


}