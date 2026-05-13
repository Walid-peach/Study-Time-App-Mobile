package com.example.studytime.ui.booking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studytime.data.model.StudyHall
import com.example.studytime.databinding.ItemStudyHallBinding

class HallAdapter(private val onClick: (StudyHall) -> Unit) :
    ListAdapter<StudyHall, HallAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(private val binding: ItemStudyHallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hall: StudyHall) {
            binding.tvHallName.text = hall.name
            binding.tvHallLocation.text = hall.location
            binding.tvHallCapacity.text = binding.root.context.getString(
                com.example.studytime.R.string.hall_capacity, hall.capacity
            )
            binding.root.setOnClickListener { onClick(hall) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemStudyHallBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<StudyHall>() {
            override fun areItemsTheSame(a: StudyHall, b: StudyHall) = a.id == b.id
            override fun areContentsTheSame(a: StudyHall, b: StudyHall) = a == b
        }
    }
}
