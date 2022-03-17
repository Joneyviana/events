package com.example.events.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.events.R
import com.example.events.constants.EventAppConstants.EVENT_ID
import com.example.events.data.Event


/**
 * @author WellingtonCosta on 18/07/18
 */
class EventsAdapter(
    var events: List<Event>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EventsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.event_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as EventsViewHolder).binding
        binding?.event = events[position]
        binding?.executePendingBindings()
        val bundle = Bundle().apply { putString(EVENT_ID, events[position].id) }
        holder.itemView.setOnClickListener { it.findNavController().navigate(R.id.event_detail, bundle) }
    }

    override fun getItemCount() = events.size
}
