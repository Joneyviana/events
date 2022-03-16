package com.example.events.presentation

import android.support.test.rule.ActivityTestRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.events.R
import com.example.events.data.Event
import com.example.events.data.EventRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventsFragmentTest {
    private var events: List<Event>? = null
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun setup(): Unit = runBlocking {
        val eventRepository = EventRepository()
        events = eventRepository.fetchEvents().firstOrNull()
    }

    @Test
    fun shouldLoadEvents() {
        assertNotNull(events)
        events?.forEach {
            onView(withId(R.id.recycler_view_events))
                .perform(
                    RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>
                        (hasDescendant(withText(it.title)), scrollTo())
                );
        }
    }

    @Test
    fun shouldChangeFragmentOnClickItem() {
        onView(withId(R.id.checkin_button)).check(doesNotExist())
        onView(withId(R.id.recycler_view_events))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.checkin_button)).check(matches(isDisplayed()))

    }


}