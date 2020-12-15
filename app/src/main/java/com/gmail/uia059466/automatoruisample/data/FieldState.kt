package com.gmail.uia059466.automatoruisample.data

sealed class FieldState {
  object Valid     : FieldState()
  data class  Invalid(val resError: Int) : FieldState()
  object Empty       : FieldState()
}