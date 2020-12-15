package com.gmail.uia059466.automatoruisample.data

sealed class ResultRequest<out R> {
  data class Success<out T>(val data: T) : ResultRequest<T>()
  data class Error(val resError: Int) : ResultRequest<Nothing>()
}