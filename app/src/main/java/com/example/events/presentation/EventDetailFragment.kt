package com.example.events.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.events.constants.EventAppConstants.EVENT_ID
import com.example.events.databinding.EventDetailFragmentBinding
import com.example.events.extensions.toVisibility

class EventDetailFragment : Fragment() {

    private var binding: EventDetailFragmentBinding? = null
    private val viewModel by viewModels<EventDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EventDetailFragmentBinding.inflate(inflater, container, false);
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(EVENT_ID)?.let { eventId ->
            setScreenState()
            binding?.checkinButton?.setOnClickListener { viewModel.checkIn(eventId) }
            viewModel.fetchEventDetail(eventId)
        }

    }

    private fun setScreenState() {
        viewModel.eventDetail.observe(viewLifecycleOwner) {
            binding?.progressBarLoading?.visibility = it.loading.toVisibility()

            it.success?.let { event ->
                binding?.event = event
            }
        }

        viewModel.checkin.observe(viewLifecycleOwner) {
            binding?.progressBarLoading?.visibility = it.loading.toVisibility()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}