package com.example.studytime.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studytime.data.repository.AuthRepository
import com.example.studytime.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authRepo: AuthRepository
) : ViewModel() {

    private val _resetState = MutableStateFlow<Resource<Unit>?>(null)
    val resetState: StateFlow<Resource<Unit>?> = _resetState

    fun sendPasswordReset(email: String) {
        viewModelScope.launch {
            _resetState.value = Resource.Loading
            _resetState.value = authRepo.sendPasswordReset(email)
        }
    }
}
