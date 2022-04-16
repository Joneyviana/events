package com.example.events.presentation.list

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.events.databinding.EventViewItemBinding

/**
 * @author WellingtonCosta on 19/07/18
 */
class EventsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: EventViewItemBinding? = DataBindingUtil.bind(view)
}
