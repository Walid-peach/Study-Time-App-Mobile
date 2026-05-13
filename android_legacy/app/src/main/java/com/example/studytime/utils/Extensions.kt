package com.example.studytime.utils

import android.view.View
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

fun View.visible() { visibility = View.VISIBLE }
fun View.gone() { visibility = View.GONE }
fun View.invisible() { visibility = View.INVISIBLE }

fun TextInputLayout.setError(msg: String?) {
    error = msg
    isErrorEnabled = msg != null
}

fun EditText.trimText(): String = text.toString().trim()
