package com.example.events.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.events.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun goToDetailScreen(): Unit = runBlocking {
    delay(200)
    Espresso.onView(ViewMatchers.withId(R.id.recycler_view_events))
        .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
}
