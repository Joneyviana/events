package com.example.events.presentation.detail

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.events.constants.EventAppConstants.EVENT_ID
import com.example.events.databinding.EventDetailFragmentBinding
import com.example.events.extensions.toVisibility
import com.example.events.presentation.checkin.CheckInDialogs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailFragment : Fragment() {

    private var eventId: String? = null
    private var binding: EventDetailFragmentBinding? = null
    private val viewModel by viewModels<EventDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EventDetailFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(EVENT_ID)?.let {
            setScreenState()
            eventId = it
            binding?.checkinButton?.setOnClickListener { viewModel.checkIn(eventId) }
            viewModel.fetchEventDetail(it)
        }

    }

    private fun setScreenState() {
        val failurelayout = binding?.failureLayout
        failurelayout?.reloadButton?.setOnClickListener {
            eventId?.let { viewModel.fetchEventDetail(it) }
        }

        viewModel.eventDetail.observe(viewLifecycleOwner) {
            binding?.progressBarLoading?.visibility = it.loading.toVisibility()
            failurelayout?.root?.visibility = it.failed.toVisibility()
            it.success?.let { event ->
                binding?.event = event
            }
        }

        viewModel.checkIn.observe(viewLifecycleOwner) { response ->
            binding?.progressBarLoading?.visibility = response.loading.toVisibility()
            activity?.let { context ->
                response.success?.let {
                    CheckInDialogs().showSuccessDialog(context)
                } ?: run {
                    if (response.failed) {
                        CheckInDialogs().showFailedDialog(context)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}