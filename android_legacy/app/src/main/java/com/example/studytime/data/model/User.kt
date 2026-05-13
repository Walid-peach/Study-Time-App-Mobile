package com.example.studytime.data.model

import androidx.annotation.Keep

@Keep
data class User(
    val uid: String = "",
    val fullName: String = "",
    val email: String = "",
    val department: String = ""
)
