package com.example.events.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.events.R
import com.example.events.constants.EventAppConstants.EVENT_ID
import com.example.events.databinding.EventDetailFragmentBinding
import com.example.events.databinding.EventsFragmentBinding

class EventDetailFragment : Fragment() {

    companion object {
        fun newInstance() = EventDetailFragment()
    }

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
        arguments?.getString(EVENT_ID)?.let {
            setScreenState()
            viewModel.fetchEventDetail(it)
        }

    }

    private fun setScreenState() {
        viewModel.eventDetail.observe(viewLifecycleOwner) {
            binding?.event = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}