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

    suspend fun createBooking(booking: Booking): Resource<String> = try {
        val data = booking.copy(createdAt = Timestamp.now())
        val ref = bookingsCollection().add(data).await()
        Resource.Success(ref.id)
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
