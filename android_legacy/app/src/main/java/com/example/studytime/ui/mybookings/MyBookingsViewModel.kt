package com.example.studytime.ui.mybookings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studytime.data.model.Booking
import com.example.studytime.data.repository.AuthRepository
import com.example.studytime.data.repository.BookingRepository
import com.example.studytime.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyBookingsViewModel @Inject constructor(
    private val bookingRepo: BookingRepository,
    private val authRepo: AuthRepository
) : ViewModel() {

    private val _bookings = MutableStateFlow<Resource<List<Booking>>>(Resource.Loading)
    val bookings: StateFlow<Resource<List<Booking>>> = _bookings

    private val _cancelState = MutableStateFlow<Resource<Unit>?>(null)
    val cancelState: StateFlow<Resource<Unit>?> = _cancelState

    init { loadBookings() }

    fun loadBookings() {
        val uid = authRepo.currentUser?.uid ?: return
        viewModelScope.launch {
            _bookings.value = Resource.Loading
            _bookings.value = bookingRepo.getUserBookings(uid)
        }
    }

    fun cancelBooking(bookingId: String) {
        viewModelScope.launch {
            _cancelState.value = Resource.Loading
            _cancelState.value = bookingRepo.cancelBooking(bookingId)
            if (_cancelState.value is Resource.Success) loadBookings()
        }
    }

    fun resetCancelState() { _cancelState.value = null }
}
