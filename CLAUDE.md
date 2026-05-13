# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
./gradlew assembleDebug          # Build debug APK
./gradlew assembleRelease        # Build release APK
./gradlew test                   # Run unit tests
./gradlew connectedAndroidTest   # Run instrumented tests (requires device/emulator)
./gradlew lint                   # Run lint checks
./gradlew clean                  # Clean build
```

**Build config:** compileSdk 35, minSdk 24, targetSdk 35, Kotlin 1.9.22, AGP 8.2.2

## Architecture

**Single-Activity MVVM** with Jetpack Navigation, Hilt DI, Kotlin Coroutines, and Firebase.

```
app/src/main/java/com/example/studytime/
├── StudyTimeApp.kt              # Hilt @HiltAndroidApp application class
├── MainActivity.kt              # Single activity — hosts NavHostFragment + Toolbar
├── di/AppModule.kt              # Provides FirebaseAuth, FirebaseFirestore singletons
├── data/
│   ├── model/                   # Pure data classes: User, StudyHall, Booking, TimeSlot
│   └── repository/              # AuthRepository, UserRepository, StudyHallRepository, BookingRepository
├── ui/
│   ├── auth/                    # LoginFragment, RegisterFragment, ResetPasswordFragment (+ ViewModels)
│   ├── dashboard/               # DashboardFragment + DashboardViewModel
│   ├── booking/                 # HallSelectionFragment, TableSelectionFragment, TimeSlotFragment,
│   │                            # ConfirmationFragment — all share BookingViewModel via activityViewModels()
│   ├── mybookings/              # MyBookingsFragment + ViewModel + BookingItemAdapter
│   └── profile/                 # ProfileFragment + ProfileViewModel
└── utils/
    ├── Resource.kt              # Sealed class: Success<T>, Error, Loading
    └── Extensions.kt            # View visibility helpers, TextInputLayout.setError(), EditText.trimText()
```

## Key Patterns

**State flow:** Every ViewModel exposes `StateFlow<Resource<T>?>`. Fragments collect it in `viewLifecycleOwner.lifecycleScope.launch { ... }`. `Resource.Loading` shows a ProgressBar; `Resource.Error` shows a Snackbar; `Resource.Success` updates the UI.

**Shared ViewModel across booking steps:** `BookingViewModel` is created at the activity scope via `activityViewModels()`. It holds `selectedHall`, `selectedTable`, `selectedSeat`, `selectedDate`, `selectedTimeSlot` as plain mutable properties set by each fragment as the user progresses through the flow.

**Repository pattern:** All Firebase calls live in repositories (`data/repository/`). They return `Resource<T>` using `try/catch` around `kotlinx.coroutines.tasks.await()`. ViewModels never touch Firebase directly.

**Navigation:** `res/navigation/nav_graph.xml` defines all routes. Auth screens pop back to `loginFragment` when navigating to dashboard so the back stack doesn't leak auth screens.

## Firebase Data Model (Firestore)

```
users/{uid}          → User { uid, fullName, email, department }
studyHalls/{id}      → StudyHall { id, name, location, capacity, tableCount }
bookings/{id}        → Booking { userId, hallId, hallName, tableNumber, seatNumber,
                                 date (ISO yyyy-MM-dd), timeSlotLabel, startHour,
                                 durationMinutes, createdAt, status ("active"|"cancelled") }
```

**Firestore indexes needed:** `bookings` collection requires a composite index on `(hallId, tableNumber, date, status)` and `(userId, createdAt DESC)` for the My Bookings query.

## Localization

English strings in `res/values/strings.xml`, French translations in `res/values-fr/strings.xml`. All user-facing text must go through string resources — no hardcoded strings in Kotlin or XML layouts.

## Dependencies (key)

| Library | Purpose |
|---|---|
| Hilt 2.50 | Dependency injection |
| Navigation 2.7.6 | Fragment navigation |
| Firebase BOM 32.7.0 | Auth + Firestore |
| Coroutines + `kotlinx-coroutines-play-services` | Async Firebase calls via `.await()` |
| Material 1.11.0 | UI components, MaterialDatePicker |
| Lottie 6.3.0 | Animations (raw/ JSON assets kept from original) |
