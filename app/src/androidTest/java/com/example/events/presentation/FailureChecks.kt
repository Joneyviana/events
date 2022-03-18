package com.example.events.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.events.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun failureViewIsVisible(): Unit = runBlocking {
    delay(100)
    onView(ViewMatchers.withId(R.id.failure_layout))
        .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
}

fun failureViewIsGone(): Unit = runBlocking {
    delay(100)
    onView(ViewMatchers.withId(R.id.failure_layout))
        .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
}