package com.example.events.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.events.R
import com.example.events.constants.EventAppConstants.EVENT_ID
import com.example.events.data.EventRepository
import com.example.events.di.NetworkModule
import com.example.events.fakedata.FakeDataResponse
import com.example.events.fakedata.FakeEventApi
import com.example.events.presentation.detail.EventDetailViewModel
import com.example.events.room.RoomMemoryModule
import com.example.events.utils.executeOnUI
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class EventFragmentDetailTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()


    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)

    private val appDatabase = RoomMemoryModule.provideAppDatabase(ApplicationProvider.getApplicationContext())

    @BindValue
    var viewModel: EventDetailViewModel = mockk(relaxed = true)

    @Before
    fun init() {
        executeOnUI {
            viewModel = getEventDetailViewModel()
        }
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun shouldLoadEventDetail(): Unit = runBlocking {
        goToDetailScreen()
        onView(withId(R.id.checkin_button)).check(matches(isDisplayed()))
        onView(withText("title 1")).check(matches(isDisplayed()))
        onView(withText("price: 2.74")).check(matches(isDisplayed()))

        //Porto Alegre is at the address based on the given latitude and longitude
        onView(withText(containsString("Porto Alegre"))).check(matches(isDisplayed()))
        onView(withText("description 1")).check(doesNotExist())
    }

    @Test
    fun shouldMakeChekIn() {
        goToDetailScreen()
        onView(withId(R.id.checkin_button)).perform(scrollTo(), click())
        onView(withText("Success!!")).check(matches(isDisplayed())).perform(pressBack())
        FakeDataResponse.setFailureAsResponse()
        onView(withId(R.id.checkin_button)).perform(click())
        onView(withText("Failed!!")).check(matches(isDisplayed())).perform(pressBack())

        FakeDataResponse.removeFailureAsResponse()
    }

    @Test
    fun shouldShowReloadOptionOnFailure() {
        goToDetailScreen()
        failureViewIsGone()

        FakeDataResponse.setFailureAsResponse()
        appDatabase.eventDao().deleteAll()
        executeOnUI { viewModel.fetchEventDetail("0") }
        failureViewIsVisible()

        FakeDataResponse.removeFailureAsResponse()
        executeOnUI { viewModel.fetchEventDetail("0") }
        failureViewIsGone()
    }

    private fun getEventDetailViewModel(): EventDetailViewModel {
        every { savedStateHandle.get<String>(EVENT_ID) } returns "0"
        return EventDetailViewModel(EventRepository(FakeEventApi(), appDatabase.eventDao()), savedStateHandle)
    }
}