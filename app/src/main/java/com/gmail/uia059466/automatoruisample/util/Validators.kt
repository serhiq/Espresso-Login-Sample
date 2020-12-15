package com.gmail.uia059466.automatoruisample.util

import android.util.Patterns
import com.gmail.uia059466.automatoruisample.data.FieldState
import com.gmail.uia059466.automatoruisample.R

private fun isEmailValid(email: String): Boolean {
  return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

private fun isPasswordValid(password: String): Boolean {
  return password.length > 5
}

fun checkPassword(password: String): FieldState {
  return when {
    password.isBlank()         -> FieldState.Invalid(R.string.missing_password)
    !isPasswordValid(password) -> FieldState.Invalid(R.string.invalid_password)
    isPasswordValid(password)  -> FieldState.Valid
    else -> FieldState.Empty
  }
}

fun checkEmail(email: String): FieldState {
  return when {
    email.isBlank()      -> FieldState.Invalid(R.string.missing_email_address)
    !isEmailValid(email) -> FieldState.Invalid(R.string.invalid_email)
    isEmailValid(email)  -> FieldState.Valid
    else                 -> FieldState.Empty
  }
}