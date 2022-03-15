package com.example.events.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.events.databinding.EventsFragmentBinding

class EventsFragment : Fragment() {

    companion object {
        fun newInstance() = EventsFragment()
    }

    private var binding: EventsFragmentBinding? = null
    private val viewModel by viewModels<EventViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EventsFragmentBinding.inflate(inflater, container, false);
        return binding?.root
        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setScreenState()

    }

    private fun setScreenState() {
        viewModel.events.observe(viewLifecycleOwner) {
            val eventsAdapter = EventsAdapter(it)
            binding?.recyclerViewEvents?.apply {
                adapter = eventsAdapter
                layoutManager = LinearLayoutManager(activity)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}