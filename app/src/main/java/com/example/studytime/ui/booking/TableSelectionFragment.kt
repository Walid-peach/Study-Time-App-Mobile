package com.example.studytime.ui.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.studytime.R
import com.example.studytime.databinding.FragmentTableSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableSelectionFragment : Fragment() {

    private var _binding: FragmentTableSelectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookingViewModel by activityViewModels()

    private var selectedTable = 0
    private var selectedSeat = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTableSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvHallName.text = viewModel.selectedHall?.name ?: ""

        val tableButtons = listOf(
            binding.btnTable1, binding.btnTable2, binding.btnTable3,
            binding.btnTable4, binding.btnTable5, binding.btnTable6,
            binding.btnTable7, binding.btnTable8, binding.btnTable9,
            binding.btnTable10
        )

        tableButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                selectedTable = index + 1
                tableButtons.forEach { b -> b.isSelected = false }
                btn.isSelected = true
                updateSeatPanel(selectedTable)
            }
        }

        val seatButtons = listOf(
            binding.btnSeat1, binding.btnSeat2, binding.btnSeat3,
            binding.btnSeat4, binding.btnSeat5, binding.btnSeat6,
            binding.btnSeat7, binding.btnSeat8
        )

        seatButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                selectedSeat = index + 1
                seatButtons.forEach { b -> b.isSelected = false }
                btn.isSelected = true
            }
        }

        binding.btnContinue.setOnClickListener {
            if (selectedTable == 0) {
                Toast.makeText(requireContext(), getString(R.string.select_table_prompt), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (selectedSeat == 0) {
                Toast.makeText(requireContext(), getString(R.string.select_seat_prompt), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.selectedTable = selectedTable
            viewModel.selectedSeat = selectedSeat
            findNavController().navigate(R.id.action_tableSelection_to_timeSlot)
        }

        // Date picker
        binding.tvSelectedDate.text = viewModel.selectedDate
        binding.btnChangeDate.setOnClickListener { showDatePicker() }
    }

    private fun updateSeatPanel(table: Int) {
        binding.seatPanel.visibility = View.VISIBLE
        binding.tvTableLabel.text = getString(R.string.table_number, table)
    }

    private fun showDatePicker() {
        val picker = com.google.android.material.datepicker.MaterialDatePicker.Builder
            .datePicker()
            .setTitleText(getString(R.string.select_date))
            .setSelection(com.google.android.material.datepicker.MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        picker.addOnPositiveButtonClickListener { millis ->
            val date = java.time.Instant.ofEpochMilli(millis)
                .atZone(java.time.ZoneOffset.UTC)
                .toLocalDate()
                .format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE)
            viewModel.selectedDate = date
            binding.tvSelectedDate.text = date
        }
        picker.show(parentFragmentManager, "date_picker")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
