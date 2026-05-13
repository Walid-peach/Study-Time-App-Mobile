package com.example.studytime.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studytime.data.model.User
import com.example.studytime.data.model.UserProfileUpdate
import com.example.studytime.data.repository.AuthRepository
import com.example.studytime.data.repository.UserRepository
import com.example.studytime.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val userRepo: UserRepository
) : ViewModel() {

    private val _user = MutableStateFlow<Resource<User>>(Resource.Loading)
    val user: StateFlow<Resource<User>> = _user

    private val _updateState = MutableStateFlow<Resource<Unit>?>(null)
    val updateState: StateFlow<Resource<Unit>?> = _updateState

    init { loadProfile() }

    fun loadProfile() {
        val uid = authRepo.currentUser?.uid ?: return
        viewModelScope.launch {
            _user.value = Resource.Loading
            _user.value = userRepo.getUser(uid)
        }
    }

    fun updateProfile(fullName: String, department: String) {
        val uid = authRepo.currentUser?.uid ?: return
        viewModelScope.launch {
            _updateState.value = Resource.Loading
            _updateState.value = userRepo.updateProfile(uid, UserProfileUpdate(fullName, department))
            if (_updateState.value is Resource.Success) loadProfile()
        }
    }

    fun resetUpdateState() { _updateState.value = null }
}
