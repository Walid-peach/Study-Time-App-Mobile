package com.example.studytime.ui.auth

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
class RegisterViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    private val _registerState = MutableStateFlow<Resource<Unit>?>(null)
    val registerState: StateFlow<Resource<Unit>?> = _registerState

    fun register(fullName: String, email: String, password: String, department: String) {
        viewModelScope.launch {
            _registerState.value = Resource.Loading
            val authResult = authRepo.register(email, password)
            if (authResult !is Resource.Success) {
                _registerState.value = Resource.Error("Registration failed")
                return@launch
            }
            val uid = authResult.data.uid
            val user = User(uid = uid, fullName = fullName, email = email, department = department)
            _registerState.value = userRepo.createUser(user)
        }
    }
}
