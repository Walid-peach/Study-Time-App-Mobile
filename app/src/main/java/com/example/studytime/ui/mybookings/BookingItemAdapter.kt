package com.example.studytime.ui.mybookings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studytime.R
import com.example.studytime.data.model.Booking
import com.example.studytime.databinding.ItemBookingBinding

class BookingItemAdapter(private val onCancel: (Booking) -> Unit) :
    ListAdapter<Booking, BookingItemAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(private val binding: ItemBookingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(booking: Booking) {
            binding.tvHallName.text = booking.hallName
            binding.tvDetails.text = binding.root.context.getString(
                R.string.booking_detail_summary,
                booking.tableNumber,
                booking.seatNumber,
                booking.date,
                booking.timeSlotLabel
            )
            val isCancelled = booking.status == Booking.STATUS_CANCELLED
            binding.chipStatus.text = if (isCancelled)
                binding.root.context.getString(R.string.status_cancelled)
            else
                binding.root.context.getString(R.string.status_active)

            binding.chipStatus.setChipBackgroundColorResource(
                if (isCancelled) R.color.status_cancelled else R.color.status_active
            )

            binding.btnCancel.isEnabled = !isCancelled
            binding.btnCancel.setOnClickListener { onCancel(booking) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemBookingBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Booking>() {
            override fun areItemsTheSame(a: Booking, b: Booking) = a.id == b.id
            override fun areContentsTheSame(a: Booking, b: Booking) = a == b
        }
    }
}
