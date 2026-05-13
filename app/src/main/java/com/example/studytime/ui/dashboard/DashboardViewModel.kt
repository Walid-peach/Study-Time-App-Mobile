package com.example.studytime.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studytime.data.model.User
import com.example.studytime.data.repository.AuthRepository
import com.example.studytime.data.repository.UserRepository
import com.example.studytime.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    init {
        loadUser()
    }

    private fun loadUser() {
        val uid = authRepo.currentUser?.uid ?: return
        viewModelScope.launch {
            val result = userRepo.getUser(uid)
            if (result is Resource.Success) _user.value = result.data
        }
    }

    fun signOut() = authRepo.signOut()
}
