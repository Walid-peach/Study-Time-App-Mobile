package com.example.studytime.ui.mybookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.studytime.R
import com.example.studytime.data.model.Booking
import com.example.studytime.databinding.FragmentMyBookingsBinding
import com.example.studytime.utils.Resource
import com.example.studytime.utils.gone
import com.example.studytime.utils.visible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyBookingsFragment : Fragment() {

    private var _binding: FragmentMyBookingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyBookingsViewModel by viewModels()
    private val adapter = BookingItemAdapter { booking -> confirmCancel(booking) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMyBookingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerBookings.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener { viewModel.loadBookings() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bookings.collect { state ->
                binding.swipeRefresh.isRefreshing = false
                when (state) {
                    is Resource.Loading -> {
                        binding.progressBar.visible()
                        binding.recyclerBookings.gone()
                        binding.tvEmpty.gone()
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        if (state.data.isEmpty()) {
                            binding.tvEmpty.visible()
                            binding.recyclerBookings.gone()
                        } else {
                            binding.tvEmpty.gone()
                            binding.recyclerBookings.visible()
                            adapter.submitList(state.data)
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.gone()
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cancelState.collect { state ->
                when (state) {
                    is Resource.Error -> {
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                        viewModel.resetCancelState()
                    }
                    is Resource.Success -> viewModel.resetCancelState()
                    else -> Unit
                }
            }
        }
    }

    private fun confirmCancel(booking: Booking) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.cancel_booking_title))
            .setMessage(getString(R.string.cancel_booking_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ -> viewModel.cancelBooking(booking.id) }
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
