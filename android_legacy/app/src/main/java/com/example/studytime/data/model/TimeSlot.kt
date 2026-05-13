package com.example.studytime.data.model

data class TimeSlot(
    val id: String = "",
    val label: String = "",
    val startHour: Int = 0,
    val startMinute: Int = 0,
    val durationMinutes: Int = 60,
    val isBooked: Boolean = false
)
