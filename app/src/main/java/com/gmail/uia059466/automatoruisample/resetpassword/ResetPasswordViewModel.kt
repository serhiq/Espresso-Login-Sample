package com.gmail.uia059466.automatoruisample.resetpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.uia059466.automatoruisample.util.SingleLiveEvent
import com.gmail.uia059466.automatoruisample.data.ResultRequest
import com.gmail.uia059466.automatoruisample.data.FieldState
import com.gmail.uia059466.automatoruisample.data.UserRepository
import com.gmail.uia059466.automatoruisample.util.checkEmail

class ResetPasswordViewModel(private val repository: UserRepository) : ViewModel() {
  
  private val _state = MutableLiveData<FieldState>()
  val state: LiveData<FieldState> = _state

  val snackbarText: SingleLiveEvent<Int> =  SingleLiveEvent<Int>()
  
  val showEmailSentDialog: SingleLiveEvent<Boolean> =  SingleLiveEvent<Boolean>()
  
  fun validateAndReset(email: String) {
    val emailState = checkEmail(email)
    
    if (emailState is FieldState.Valid) {
      resetPassword(email)
      
    } else {
      _state.value = emailState
    }
  }
  
  private fun resetPassword(email: String) {
    val result = repository.resetPassword(email)
    when (result) {
      is ResultRequest.Success -> showEmailSentDialog.postValue(true)
      is ResultRequest.Error   -> snackbarText.postValue(result.resError)
    }
  }
  
  fun emailChanged(text: String) {
    val emailState = checkEmail(text)
    _state.value = emailState
  }
}