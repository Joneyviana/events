package com.example.events.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.events.R
import com.example.events.data.Event
import com.example.events.data.EventRepository
import com.example.events.di.NetworkModule
import com.example.events.fakedata.FakeDataResponse
import com.example.events.fakedata.FakeEventApi
import com.example.events.presentation.list.EventViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class EventsFragmentTest {
    private var events: List<Event>? = null

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()


    @BindValue
    val viewModel: EventViewModel = EventViewModel(EventRepository(FakeEventApi()))

    @Before
    fun init() {
        hiltRule.inject()
        events = FakeDataResponse.getEvents().body()
    }

    @Test
    fun shouldLoadEvents() {
        assertNotNull(events)
        assertEquals(3, events?.size)
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

    @Test
    fun shouldShowReloadOptionOnFailure() {
        failureViewIsGone()

        FakeDataResponse.setFailure()
        viewModel.fetchEvents()
        failureViewIsVisible()

        FakeDataResponse.removeFailure()
        viewModel.fetchEvents()
        failureViewIsGone()

    }


}