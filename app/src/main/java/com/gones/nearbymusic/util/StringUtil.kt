package com.gones.nearbymusic.util

import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
    val pattern = Pattern.compile(passwordPattern)
    val matcher = pattern.matcher(this)
    return toString().length >= 8
}