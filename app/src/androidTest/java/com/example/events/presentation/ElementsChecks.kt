package com.example.events.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import com.example.events.R
import com.example.events.fakedata.FakeDataResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun failureViewIsVisible(): Unit = runBlocking {
    delay(200)
    onView(ViewMatchers.withId(R.id.failure_layout))
        .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
}

fun failureViewIsGone(): Unit = runBlocking {
    delay(200)
    onView(ViewMatchers.withId(R.id.failure_layout))
        .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
}

fun textNotFoundIsGone(): Unit = runBlocking {
    delay(200)
    onView(ViewMatchers.withId(R.id.events_not_found_text))
        .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
}

fun textNotFoundIsVisible(): Unit = runBlocking {
    delay(200)
    onView(ViewMatchers.withId(R.id.events_not_found_text))
        .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
}

fun listEventsIsLoaded(): Unit = runBlocking {
    delay(200)
    FakeDataResponse.getEvents().body()?.forEach {
        onView(ViewMatchers.withId(R.id.recycler_view_events))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>
                    (ViewMatchers.hasDescendant(ViewMatchers.withText(it.title)), ViewActions.scrollTo())
            );
    }
}