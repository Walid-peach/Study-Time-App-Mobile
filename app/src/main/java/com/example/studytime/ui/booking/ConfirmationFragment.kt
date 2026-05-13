package com.example.studytime.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.studytime.R
import com.example.studytime.databinding.FragmentConfirmationBinding
import com.example.studytime.utils.Resource
import com.example.studytime.utils.gone
import com.example.studytime.utils.visible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConfirmationFragment : Fragment() {

    private var _binding: FragmentConfirmationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookingViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHall.text = viewModel.selectedHall?.name ?: ""
        binding.tvTable.text = getString(R.string.table_number, viewModel.selectedTable)
        binding.tvSeat.text = getString(R.string.seat_number, viewModel.selectedSeat)
        binding.tvDate.text = viewModel.selectedDate
        binding.tvTimeSlot.text = viewModel.selectedTimeSlot?.label ?: ""

        binding.btnConfirmBooking.setOnClickListener {
            viewModel.confirmBooking()
        }

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_confirmation_to_dashboard)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.confirmState.collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.btnConfirmBooking.isEnabled = false
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        binding.successView.visible()
                        binding.detailsCard.gone()
                        binding.btnConfirmBooking.gone()
                        binding.btnCancel.text = getString(R.string.back_to_home)
                        viewModel.resetConfirmState()
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        binding.btnConfirmBooking.isEnabled = true
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                        viewModel.resetConfirmState()
                    }
                    null -> {
                        binding.progressBar.gone()
                        binding.btnConfirmBooking.isEnabled = true
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
