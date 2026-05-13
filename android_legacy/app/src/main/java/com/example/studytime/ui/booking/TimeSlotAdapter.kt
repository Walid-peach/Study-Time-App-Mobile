package com.example.studytime.ui.booking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studytime.R
import com.example.studytime.data.model.TimeSlot
import com.example.studytime.databinding.ItemTimeSlotBinding

class TimeSlotAdapter(private val onClick: (TimeSlot) -> Unit) :
    ListAdapter<TimeSlot, TimeSlotAdapter.ViewHolder>(DIFF) {

    private var selectedId: String? = null

    inner class ViewHolder(private val binding: ItemTimeSlotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(slot: TimeSlot) {
            binding.tvSlotLabel.text = slot.label
            val isSelected = slot.id == selectedId

            when {
                slot.isBooked -> {
                    binding.root.isEnabled = false
                    binding.root.alpha = 0.4f
                    binding.root.setCardBackgroundColor(
                        ContextCompat.getColor(binding.root.context, R.color.slot_booked)
                    )
                }
                isSelected -> {
                    binding.root.isEnabled = true
                    binding.root.alpha = 1f
                    binding.root.setCardBackgroundColor(
                        ContextCompat.getColor(binding.root.context, R.color.slot_selected)
                    )
                }
                else -> {
                    binding.root.isEnabled = true
                    binding.root.alpha = 1f
                    binding.root.setCardBackgroundColor(
                        ContextCompat.getColor(binding.root.context, R.color.slot_available)
                    )
                }
            }

            binding.root.setOnClickListener {
                if (!slot.isBooked) {
                    selectedId = slot.id
                    notifyDataSetChanged()
                    onClick(slot)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemTimeSlotBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<TimeSlot>() {
            override fun areItemsTheSame(a: TimeSlot, b: TimeSlot) = a.id == b.id
            override fun areContentsTheSame(a: TimeSlot, b: TimeSlot) = a == b
        }
    }
}
