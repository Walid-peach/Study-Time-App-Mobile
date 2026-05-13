# StudyTime

A modern Android app for booking study spaces at your university — reserve a hall, table, seat, and time slot in a few taps.

---

## Features

- **Authentication** — Sign up, sign in, forgot password (Firebase Auth)
- **Browse study halls** — Pull-to-refresh list of available spaces
- **Step-by-step booking** — Pick hall → table → seat → date → time slot
- **Real-time availability** — Time slots marked as booked automatically
- **My Bookings** — View your upcoming and past reservations, cancel any active one
- **Editable profile** — Update your name and department
- **Bilingual** — Full English and French localization

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| Architecture | MVVM + Repository |
| Dependency injection | Hilt |
| Navigation | Jetpack Navigation Component |
| Async | Coroutines + Flow |
| Backend | Firebase Auth + Firestore |
| UI | Material Design 3, View Binding |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 35 (Android 15) |

---

## Architecture

```
com.example.studytime/
│
├── StudyTimeApp.kt              ← @HiltAndroidApp entry point
├── MainActivity.kt              ← Single activity, hosts NavHostFragment
│
├── di/
│   └── AppModule.kt             ← Provides FirebaseAuth, FirebaseFirestore
│
├── data/
│   ├── model/                   ← Pure Kotlin data classes
│   │   ├── User.kt
│   │   ├── StudyHall.kt
│   │   ├── Booking.kt
│   │   └── TimeSlot.kt
│   │
│   └── repository/              ← All Firebase I/O lives here
│       ├── AuthRepository.kt
│       ├── UserRepository.kt
│       ├── StudyHallRepository.kt
│       └── BookingRepository.kt
│
├── ui/
│   ├── auth/                    ← Login, Register, Reset Password
│   ├── dashboard/               ← Home screen
│   ├── booking/                 ← Hall → Table → TimeSlot → Confirmation
│   │                               (all share BookingViewModel via activityViewModels)
│   ├── mybookings/              ← Booking list + cancel
│   └── profile/                 ← View + edit profile
│
└── utils/
    ├── Resource.kt              ← Sealed class: Loading / Success<T> / Error
    └── Extensions.kt            ← View helpers (visible/gone, trimText, etc.)
```

### Data flow

```
Fragment  ──observe──▶  ViewModel  ──suspend fun──▶  Repository  ──await()──▶  Firestore
   │                       │
   └── collect Resource    └── emits StateFlow<Resource<T>>
```

Every ViewModel exposes `StateFlow<Resource<T>?>`. Fragments collect the flow inside `viewLifecycleOwner.lifecycleScope`, then show a `ProgressBar` on `Loading`, update the UI on `Success`, and show a `Snackbar` on `Error`.

---

## Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17
- A Firebase project with **Authentication** (Email/Password) and **Firestore** enabled

### Setup

1. Clone the repository
   ```bash
   git clone <repo-url>
   cd Study-Time-App-Mobile
   ```

2. Add your `google-services.json` to `app/` (download from Firebase Console → Project Settings)

3. Create the required Firestore indexes in the Firebase Console:

   | Collection | Fields | Order |
   |---|---|---|
   | `bookings` | `userId` ASC, `createdAt` DESC | — |
   | `bookings` | `hallId` ASC, `tableNumber` ASC, `date` ASC, `status` ASC | — |

4. Seed at least one `studyHalls` document in Firestore:
   ```json
   {
     "name": "Library Hall A",
     "location": "Building 1, Floor 2",
     "capacity": 80,
     "tableCount": 10
   }
   ```

5. Open in Android Studio, let Gradle sync, then run on a device or emulator.

---

## Firestore Data Model

```
users/
  {uid}/
    uid: string
    fullName: string
    email: string
    department: string

studyHalls/
  {id}/
    name: string
    location: string
    capacity: int
    tableCount: int

bookings/
  {id}/
    userId: string
    hallId: string
    hallName: string
    tableNumber: int
    seatNumber: int
    date: string          ← ISO format: "2026-05-13"
    timeSlotLabel: string ← e.g. "9:00 AM – 10:00 AM"
    startHour: int        ← 8–21
    durationMinutes: int
    createdAt: Timestamp
    status: string        ← "active" | "cancelled"
```

---

## Build Commands

```bash
./gradlew assembleDebug          # Debug APK
./gradlew assembleRelease        # Release APK (minified)
./gradlew test                   # Unit tests
./gradlew connectedAndroidTest   # Instrumented tests
./gradlew lint                   # Lint
```

---

## Localization

| Locale | File |
|---|---|
| English (default) | `app/src/main/res/values/strings.xml` |
| French | `app/src/main/res/values-fr/strings.xml` |

The app auto-selects the locale based on the device language. To add a new language, create `values-<locale>/strings.xml` and translate all entries.
