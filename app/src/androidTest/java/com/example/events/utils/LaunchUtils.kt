package com.example.events.utils

import androidx.test.core.app.ActivityScenario
import com.example.events.data.local.AppDatabase
import com.example.events.fakedata.FakeDataResponse
import com.example.events.fakedata.FakeEventApi
import com.example.events.presentation.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun launchMainActivityWithNetworkFailure() {
    FakeDataResponse.setFailureAsResponse()
    launchMainActivity()
}

fun launchMainActivityWithEmptyList() {
    FakeDataResponse.setEmptyAsResponse()
    launchMainActivity()
}

fun launchMainActivityWithOfflineData(appDatabase: AppDatabase): Unit = runBlocking {
    FakeEventApi().getEvents().body()?.let { appDatabase.eventDao().insertAll(it) }
    launchMainActivityWithNetworkFailure()
}

fun launchMainActivity(): Unit = runBlocking {
    delay(200)
    ActivityScenario.launch(MainActivity::class.java, null)
    delay(200)
}
