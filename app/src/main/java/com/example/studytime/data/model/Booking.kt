package com.example.studytime.data.model

import com.google.firebase.Timestamp

data class Booking(
    val id: String = "",
    val userId: String = "",
    val hallId: String = "",
    val hallName: String = "",
    val tableNumber: Int = 0,
    val seatNumber: Int = 0,
    val date: String = "",
    val timeSlotLabel: String = "",
    val startHour: Int = 0,
    val durationMinutes: Int = 60,
    val createdAt: Timestamp? = null,
    val status: String = STATUS_ACTIVE
) {
    companion object {
        const val STATUS_ACTIVE = "active"
        const val STATUS_CANCELLED = "cancelled"
    }
}
