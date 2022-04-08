package com.example.events.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.events.R
import com.example.events.constants.EventAppConstants.EVENT_ID
import com.example.events.databinding.EventDetailFragmentBinding
import com.example.events.extensions.toVisibility
import com.example.events.presentation.checkin.CheckInDialogs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setScreenState() {
        val failurelayout = binding?.failureLayout
        failurelayout?.reloadButton?.setOnClickListener {
            eventId?.let { viewModel.fetchEventDetail(it) }
        }

        viewModel.eventDetail.observe(viewLifecycleOwner) {
            binding?.progressBarLoading?.visibility = it.loading.toVisibility()
            failurelayout?.root?.visibility = it.loadFailure.toVisibility()
            it.data?.let { event ->
                binding?.event = event
                binding?.shareButton?.setOnClickListener {
                    activity?.let { context ->
                        ShareCompat.IntentBuilder(context)
                            .setType("text/plain")
                            .setChooserTitle(getString(R.string.check_event))
                            .setText(event.getSharedText())
                            .startChooser()
                    };
                }
            }

            lifecycleScope.launchWhenStarted {
                viewModel.checkIn.collectLatest { response ->
                    binding?.progressBarLoading?.visibility = response.loading.toVisibility()
                    activity?.let { context ->
                        response.data?.let {
                            CheckInDialogs().showSuccessDialog(context)
                        } ?: run {
                            if (response.failed) {
                                CheckInDialogs().showFailedDialog(context)
                            }
                        }
                    }
                }
            }
        }
    }
}