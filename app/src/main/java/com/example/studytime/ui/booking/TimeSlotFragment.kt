package com.example.studytime.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studytime.R
import com.example.studytime.databinding.FragmentTimeSlotBinding
import com.example.studytime.utils.Resource
import com.example.studytime.utils.gone
import com.example.studytime.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimeSlotFragment : Fragment() {

    private var _binding: FragmentTimeSlotBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookingViewModel by activityViewModels()
    private val adapter = TimeSlotAdapter { slot ->
        viewModel.selectedTimeSlot = slot
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTimeSlotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvBookingInfo.text = getString(
            R.string.booking_summary_header,
            viewModel.selectedHall?.name ?: "",
            viewModel.selectedTable,
            viewModel.selectedSeat,
            viewModel.selectedDate
        )

        binding.recyclerTimeSlots.adapter = adapter
        viewModel.loadTimeSlots()

        binding.btnConfirm.setOnClickListener {
            if (viewModel.selectedTimeSlot == null) {
                Toast.makeText(requireContext(), getString(R.string.select_time_prompt), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            findNavController().navigate(R.id.action_timeSlot_to_confirmation)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.timeSlots.collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.recyclerTimeSlots.gone()
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        binding.recyclerTimeSlots.visible()
                        adapter.submitList(state.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
