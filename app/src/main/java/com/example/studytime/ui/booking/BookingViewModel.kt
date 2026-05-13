package com.example.studytime.ui.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studytime.data.model.Booking
import com.example.studytime.data.model.StudyHall
import com.example.studytime.data.model.TimeSlot
import com.example.studytime.data.repository.AuthRepository
import com.example.studytime.data.repository.BookingRepository
import com.example.studytime.data.repository.StudyHallRepository
import com.example.studytime.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val hallRepo: StudyHallRepository,
    private val bookingRepo: BookingRepository,
    private val authRepo: AuthRepository
) : ViewModel() {

    private val _halls = MutableStateFlow<Resource<List<StudyHall>>>(Resource.Loading)
    val halls: StateFlow<Resource<List<StudyHall>>> = _halls

    private val _timeSlots = MutableStateFlow<Resource<List<TimeSlot>>>(Resource.Loading)
    val timeSlots: StateFlow<Resource<List<TimeSlot>>> = _timeSlots

    private val _confirmState = MutableStateFlow<Resource<String>?>(null)
    val confirmState: StateFlow<Resource<String>?> = _confirmState

    // Selection state
    var selectedHall: StudyHall? = null
    var selectedTable: Int = 0
    var selectedSeat: Int = 0
    var selectedDate: String = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
    var selectedTimeSlot: TimeSlot? = null

    init {
        loadHalls()
    }

    fun loadHalls() {
        viewModelScope.launch {
            _halls.value = Resource.Loading
            _halls.value = hallRepo.getHalls()
        }
    }

    fun loadTimeSlots() {
        val hall = selectedHall ?: return
        viewModelScope.launch {
            _timeSlots.value = Resource.Loading
            val bookedResult = hallRepo.getBookedSlots(
                hall.id, selectedTable, selectedSeat, selectedDate
            )
            val bookedHours = if (bookedResult is Resource.Success) bookedResult.data else emptySet()
            val slots = generateTimeSlots(bookedHours)
            _timeSlots.value = Resource.Success(slots)
        }
    }

    private fun generateTimeSlots(bookedHours: Set<Int>): List<TimeSlot> {
        return (8..21).map { hour ->
            val startDisplay = if (hour <= 12) hour else hour - 12
            val startAmPm = if (hour < 12) "AM" else "PM"
            val endHour = hour + 1
            val endDisplay = if (endHour <= 12) endHour else endHour - 12
            val endAmPm = if (endHour < 12) "AM" else "PM"
            TimeSlot(
                id = hour.toString(),
                label = "$startDisplay:00 $startAmPm – $endDisplay:00 $endAmPm",
                startHour = hour,
                startMinute = 0,
                durationMinutes = 60,
                isBooked = hour in bookedHours
            )
        }
    }

    fun confirmBooking() {
        val hall = selectedHall ?: return
        val slot = selectedTimeSlot ?: return
        val uid = authRepo.currentUser?.uid ?: return

        viewModelScope.launch {
            _confirmState.value = Resource.Loading
            val booking = Booking(
                userId = uid,
                hallId = hall.id,
                hallName = hall.name,
                tableNumber = selectedTable,
                seatNumber = selectedSeat,
                date = selectedDate,
                timeSlotLabel = slot.label,
                startHour = slot.startHour,
                durationMinutes = slot.durationMinutes
            )
            _confirmState.value = bookingRepo.createBooking(booking)
        }
    }

    fun resetConfirmState() { _confirmState.value = null }
}
