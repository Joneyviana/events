package com.example.events.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.events.R
import com.example.events.data.Event


class EventsAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val events = events[position]
        val view = holder.view
        view.setOnClickListener {

        }
    }

    override fun getItemCount() = events.size
}