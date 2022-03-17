package com.example.events.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.events.databinding.EventsFragmentBinding
import com.example.events.extensions.toVisibility
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EventsFragment : Fragment() {

    private var binding: EventsFragmentBinding? = null
    private val viewModel by viewModels<EventViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EventsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreenState()

    }

    private fun setScreenState() {
        val linearLayoutManager = LinearLayoutManager(activity)
        val failurelayout = binding?.failureLayout
        failurelayout?.reloadButton?.setOnClickListener { viewModel.fetchEvents() }

        viewModel.events.observe(viewLifecycleOwner) {
            binding?.progressBarLoading?.visibility = it.loading.toVisibility()
            failurelayout?.root?.visibility = it.failed.toVisibility()

            it.success?.let { event ->
                val eventsAdapter = EventsAdapter(event)
                binding?.recyclerViewEvents?.apply {
                    adapter = eventsAdapter
                    layoutManager = linearLayoutManager
                    addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}