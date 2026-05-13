package com.example.studytime.data.repository

import com.example.studytime.data.model.StudyHall
import com.example.studytime.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudyHallRepository @Inject constructor(private val db: FirebaseFirestore) {

    suspend fun getHalls(): Resource<List<StudyHall>> = try {
        val snapshot = db.collection("studyHalls").get().await()
        val halls = snapshot.documents.mapNotNull { doc ->
            doc.toObject(StudyHall::class.java)?.copy(id = doc.id)
        }
        Resource.Success(halls)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Could not load study halls")
    }

    suspend fun getBookedSlots(hallId: String, tableNumber: Int, seatNumber: Int, date: String): Resource<Set<Int>> = try {
        val snapshot = db.collection("bookings")
            .whereEqualTo("hallId", hallId)
            .whereEqualTo("tableNumber", tableNumber)
            .whereEqualTo("seatNumber", seatNumber)
            .whereEqualTo("date", date)
            .whereEqualTo("status", "active")
            .get().await()
        val booked = snapshot.documents.mapNotNull {
            it.getLong("startHour")?.toInt()
        }.toSet()
        Resource.Success(booked)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Could not load time slots")
    }
}
