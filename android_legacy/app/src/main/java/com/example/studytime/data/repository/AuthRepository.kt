package com.example.studytime.data.repository

import com.example.studytime.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val auth: FirebaseAuth) {

    val currentUser: FirebaseUser? get() = auth.currentUser

    suspend fun login(email: String, password: String): Resource<FirebaseUser> = try {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        val user = result.user ?: return Resource.Error("Login succeeded but user is null")
        Resource.Success(user)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Login failed")
    }

    suspend fun register(email: String, password: String): Resource<FirebaseUser> = try {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val user = result.user ?: return Resource.Error("Registration succeeded but user is null")
        Resource.Success(user)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Registration failed")
    }

    suspend fun sendPasswordReset(email: String): Resource<Unit> = try {
        auth.sendPasswordResetEmail(email).await()
        Resource.Success(Unit)
    } catch (e: Exception) {
        Resource.Error(e.message ?: "Could not send reset email")
    }

    fun signOut() = auth.signOut()
}
