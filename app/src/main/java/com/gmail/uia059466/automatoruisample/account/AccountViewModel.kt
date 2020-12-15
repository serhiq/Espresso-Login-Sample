package com.gmail.uia059466.automatoruisample.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.uia059466.automatoruisample.util.SingleLiveEvent
import com.gmail.uia059466.automatoruisample.data.ResultRequest
import com.gmail.uia059466.automatoruisample.data.UserRepository

class AccountViewModel(private val repository: UserRepository) : ViewModel() {
  
  private val _currentUser = MutableLiveData<String>()
  val currentUser: LiveData<String> = _currentUser
  
  val snackbarText: SingleLiveEvent<Int> =  SingleLiveEvent<Int>()

  val navigateToWelcome: SingleLiveEvent<Boolean> =  SingleLiveEvent<Boolean>()
  
  init {
    val result = repository.requestCurrentUser()
    if (result is ResultRequest.Success) {
      _currentUser.postValue(result.data)
    } else if (result is ResultRequest.Error) {
      navigateToWelcome.postValue(true)
    }
  }
  
  fun deleteUser(email: String, password: String) {
    val result = repository.delete(email, password)
    when (result) {
      is ResultRequest.Success -> navigateToWelcome.postValue(true)
      is ResultRequest.Error   -> snackbarText.postValue(result.resError)
    }
  }
  
  fun signOut() {
    val result = repository.signOut()
    when (result) {
      is ResultRequest.Success -> navigateToWelcome.postValue(true)
      is ResultRequest.Error   -> snackbarText.postValue(result.resError)
    }
  }
}