package com.example.events.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.events.R
import com.example.events.data.local.AppDatabase
import com.example.events.data.local.Event
import com.example.events.di.DatabaseModule
import com.example.events.di.NetworkModule
import com.example.events.fakedata.FakeDataResponse
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@UninstallModules(NetworkModule::class, DatabaseModule::class)
@HiltAndroidTest
class ListEventTest {
    private var events: List<Event>? = null

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Inject
    lateinit var appDatabase: AppDatabase

    @Before
    fun init() {
        hiltRule.inject()
        events = FakeDataResponse.getEvents().body()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun shouldLoadEvents(): Unit = runBlocking {
        textNotFoundIsGone()
        failureViewIsGone()
        listEventsIsLoaded()
    }

    @Test
    fun shouldChangeFragmentOnClickItem(): Unit = runBlocking {
        onView(withId(R.id.checkin_button)).check(doesNotExist())
        onView(withId(R.id.recycler_view_events))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.checkin_button)).check(matches(isDisplayed()))
    }
}
