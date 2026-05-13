package com.example.studytime.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studytime.data.repository.AuthRepository
import com.example.studytime.utils.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepo: AuthRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginState: StateFlow<Resource<FirebaseUser>?> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading
            _loginState.value = authRepo.login(email, password)
        }
    }

    fun isLoggedIn() = authRepo.currentUser != null
}
