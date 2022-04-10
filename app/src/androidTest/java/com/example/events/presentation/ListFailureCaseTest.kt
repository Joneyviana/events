package com.example.events.presentation

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.events.data.EventRepository
import com.example.events.di.NetworkModule
import com.example.events.fakedata.FakeDataResponse
import com.example.events.fakedata.FakeEventApi
import com.example.events.presentation.list.EventViewModel
import com.example.events.room.RoomMemoryModule
import com.example.events.utils.executeOnUI
import com.example.events.utils.launchMainActivityWithEmptyList
import com.example.events.utils.launchMainActivityWithNetworkFailure
import com.example.events.utils.launchMainActivityWithOfflineData
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class ListFailureCaseTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val appDatabase = RoomMemoryModule.provideAppDatabase(ApplicationProvider.getApplicationContext())

    @BindValue
    lateinit var viewModel: EventViewModel

    @Before
    fun init() {
        executeOnUI {
            viewModel = EventViewModel(EventRepository(FakeEventApi(), appDatabase.eventDao()))
        }
        hiltRule.inject()

    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun shouldShowReloadOptionOnFailure() {
        launchMainActivityWithNetworkFailure()
        failureViewIsVisible()

        FakeDataResponse.removeFailureAsResponse()
        executeOnUI { viewModel.loadEvents() }
        failureViewIsGone()
    }

    @Test
    fun shouldShowEmptyMessage() {
        launchMainActivityWithEmptyList()
        textNotFoundIsVisible()
        FakeDataResponse.removeEmptyAsResponse()
    }

    @Test
    fun shouldShowOfflineDate() {
        launchMainActivityWithOfflineData(appDatabase)
        textNotFoundIsGone()
        failureViewIsGone()
        listEventsIsLoaded()
    }
}