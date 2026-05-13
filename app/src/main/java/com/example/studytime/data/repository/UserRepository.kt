package com.example.studytime.data.repository

import com.example.studytime.data.model.User
import com.example.studytime.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val db: FirebaseFirestore) {

    private fun usersCollection() = db.collection("users")

    suspend fun createUser(user: User): Resource<Unit> = try {
        usersCollection().document(user.uid).set(user).await()
        Resource.Success(Unit)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Could not save user")
    }

    suspend fun getUser(uid: String): Resource<User> = try {
        val snap = usersCollection().document(uid).get().await()
        val user = snap.toObject(User::class.java)
            ?: return Resource.Error("User not found")
        Resource.Success(user)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Could not load user")
    }

    suspend fun updateUser(uid: String, updates: Map<String, Any>): Resource<Unit> = try {
        usersCollection().document(uid).update(updates).await()
        Resource.Success(Unit)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Could not update user")
    }
}
