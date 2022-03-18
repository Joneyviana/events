package com.example.events.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
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
import com.example.events.presentation.detail.EventDetailViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class EventFragmentDetailTest {
    private var events: List<Event>? = null

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @BindValue
    val viewModel: EventDetailViewModel = EventDetailViewModel(EventRepository(FakeEventApi()))

    @Before
    fun init() {
        hiltRule.inject()
        events = FakeDataResponse.getEvents().body()
        onView(withId(R.id.recycler_view_events))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun shouldLoadEventDetail() {
        onView(withId(R.id.checkin_button)).check(matches(isDisplayed()))
        onView(withText("title 1")).check(matches(isDisplayed()))
        onView(withText("description 1")).check(matches(isDisplayed()))
    }


    @Test
    fun shouldMakeChekIn(){
        onView(withId(R.id.checkin_button)).perform(click())
        onView(withText("Success!!")).check(matches(isDisplayed())).perform(pressBack())

        FakeDataResponse.setFailure()
        onView(withId(R.id.checkin_button)).perform(click())
        onView(withText("Failed!!")).check(matches(isDisplayed())).perform(pressBack())

        FakeDataResponse.removeFailure()

    }

    @Test
    fun shouldShowReloadOptionOnFailure() {
        failureViewIsGone()

        FakeDataResponse.setFailure()
        viewModel.fetchEventDetail("0")
        failureViewIsVisible()

        FakeDataResponse.removeFailure()
        viewModel.fetchEventDetail("0")
        failureViewIsGone()

    }


}