package com.example.studytime.data.repository

import com.example.studytime.data.model.Booking
import com.example.studytime.utils.Resource
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookingRepository @Inject constructor(private val db: FirebaseFirestore) {

    private fun bookingsCollection() = db.collection("bookings")

    // Uses a slot-lock document in `bookedSlots/{key}` as a mutex.
    // The transaction reads the lock doc and writes both the lock and the booking atomically,
    // so two concurrent requests for the same seat/slot can't both succeed.
    suspend fun createBooking(booking: Booking): Resource<String> = try {
        val slotKey = "${booking.hallId}_t${booking.tableNumber}_s${booking.seatNumber}_${booking.date}_h${booking.startHour}"
        val slotRef = db.collection("bookedSlots").document(slotKey)
        val bookingRef = bookingsCollection().document()
        val bookingData = booking.copy(createdAt = Timestamp.now())

        db.runTransaction { tx ->
            val slotSnap = tx.get(slotRef)
            if (slotSnap.exists()) {
                throw Exception("This seat and time slot is already booked.")
            }
            tx.set(slotRef, mapOf("bookingId" to bookingRef.id, "createdAt" to Timestamp.now()))
            tx.set(bookingRef, bookingData)
        }.await()

        Resource.Success(bookingRef.id)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Could not create booking")
    }

    suspend fun getUserBookings(userId: String): Resource<List<Booking>> = try {
        val snapshot = bookingsCollection()
            .whereEqualTo("userId", userId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get().await()
        val bookings = snapshot.documents.mapNotNull { doc ->
            doc.toObject(Booking::class.java)?.copy(id = doc.id)
        }
        Resource.Success(bookings)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Could not load bookings")
    }

    suspend fun cancelBooking(bookingId: String): Resource<Unit> = try {
        bookingsCollection().document(bookingId)
            .update("status", Booking.STATUS_CANCELLED).await()
        Resource.Success(Unit)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Could not cancel booking")
    }
}
