# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build System and Commands

**Gradle-based Android project** (Gradle 4.2.1, compileSdkVersion 30)

### Key Build Commands
- **Build debug APK**: `./gradlew assembleDebug`
- **Build release APK**: `./gradlew assembleRelease`
- **Run all tests**: `./gradlew test`
- **Run unit tests only**: `./gradlew test --tests com.example.studytime.*`
- **Run instrumented/Android tests**: `./gradlew connectedAndroidTest`
- **Run lint checks**: `./gradlew lint`
- **Clean build**: `./gradlew clean`
- **Sync dependencies**: `./gradlew dependencies`

**Build Configuration**:
- Min SDK: 17
- Target SDK: 30
- Java 8 compatibility enabled
- MultiDex enabled (supports large APKs)
- AndroidX enabled with jetifier

## Project Structure

```
app/src/main/
├── java/com/example/studytime/      # Main source code (single flat package)
├── res/
│   ├── layout/                      # Activity and Fragment XML layouts
│   ├── values/                      # Strings, colors, dimensions
│   └── mipmap/                      # App icons
└── AndroidManifest.xml              # Activity declarations

app/src/test/                         # Unit tests (JUnit 4)
app/src/androidTest/                 # Instrumented tests (Espresso)
```

## Architecture Pattern: Basic Activity-Based MVC

This is a **straightforward Activity-based architecture** with minimal abstraction. No formal MVP/MVVM pattern is implemented.

### Key Characteristics:
1. **Activities as Controllers**: Each screen is an Activity that directly manages UI and business logic
2. **No ViewModel or Presenter layer**: Activities handle Firebase calls and data binding directly
3. **Static state sharing**: Activities use static methods to pass data between screens (e.g., `Salle.setChosen()`, `Booking.getPlace()`)
4. **Fragment containers**: Some flows use Fragments (BookingStep1/2/3/4 fragments within BookingNew activity)
5. **Listener interfaces**: Custom interfaces like `IAllBranchLoadListener`, `IAllSalleLoadListener`, `ITimeSlotLoadListener` for async callbacks
6. **Adapter pattern**: RecyclerView adapters (`MyAdapter`, `MytimeSlotAdapter`) handle list rendering

### Screen/Feature Flow:
- **Authentication**: MainActivity → login/Register/Reset (Firebase Auth)
- **Main Dashboard**: Splashscreen_loading → Dash (home hub)
- **Booking Flow**: Salle (choose study hall) → Booking (select table/position) → TimeActivity (pick time slot) → Confirmation
- **User Management**: profil, manage (bookings), notif (notifications)
- **Utility**: GroupSolo, DateActivity, CustomPopup (custom dialogs)

### Data Models:
- **Users.java**: User profile (fullname, age, email)
- **TimeSlot.java**: Time slot representation
- **Salle.java**: Study hall/room
- Helper/utility classes: Common.java (time conversions), MyAdapter.java (list rendering)

## Key Dependencies

### Firebase Suite (Backend)
- `com.google.firebase:firebase-auth:20.0.2` — Email/password authentication
- `com.google.firebase:firebase-database:19.6.0` — Realtime Database
- `com.google.firebase:firebase-firestore:22.0.1` — Firestore (primary data store)
- `com.google.gms:google-services:4.3.5` — Google Services plugin

### UI Libraries
- `androidx.appcompat:appcompat:1.2.0` — AppCompat support
- `com.google.android.material:material:1.3.0` — Material Design components
- `androidx.constraintlayout:constraintlayout:2.0.4` — ConstraintLayout for responsive UI
- `androidx.viewpager:viewpager:1.0.0` — ViewPager for tabbed/swipe navigation
- `com.airbnb.android:lottie:3.7.0` — Lottie animations

### Additional Libraries
- `com.jakewharton:butterknife:10.2.3` — View binding (though not heavily used)
- `com.shuhart.stepview:stepview:1.5.1` — Step indicator UI
- `com.jaredrummler:material-spinner:1.3.1` — Material spinner
- `devs.mulham.horizontalcalendar:horizontalcalendar:1.3.4` — Horizontal calendar picker
- `com.github.jhonnyx2012:horizontal-picker:1.0.6` — Time/date picker
- `pl.droidsonroids.gif:android-gif-drawable:1.2.22` — GIF support

## Test Setup

**Current Test Infrastructure**:
- **Unit Tests**: `app/src/test/` with JUnit 4 (ExampleUnitTest.java as template)
- **Instrumented Tests**: `app/src/androidTest/` with AndroidJUnit4 and Espresso (ExampleInstrumentedTest.java as template)
- **Test Runner**: `androidx.test.runner.AndroidJUnitRunner`

**Important**: Both test directories contain only placeholder examples. No actual tests are implemented for the app's features.

## Important Architecture Notes

1. **Static Data Passing**: Data flows between activities using static methods. This is fragile and should be replaced with Intent extras or a proper dependency injection/view model pattern for maintenance.

2. **Firebase Integration**: All data persistence and authentication goes through Firebase (Firestore, Realtime Database, Auth). No local database layer.

3. **Listener Callbacks**: Custom listener interfaces handle async Firebase callbacks instead of LiveData/RxJava, making threading harder to track.

4. **Fragment Usage Limited**: Only used in the multi-step BookingNew flow with step indicators; most screens are Activities.

5. **Resource Strings**: French language content throughout (activity names like "Salle", "Salon"; UI text in French). No i18n setup.

## Development Notes

- The app targets study space booking (reserving study halls/tables/time slots)
- Firebase Firestore is the primary database; Real-time Database is used but may be legacy
- No dependency injection framework (Dagger, Hilt) — dependencies are instantiated directly
- No Kotlin usage; pure Java
- Lottie animations used for loading states and interactive buttons

