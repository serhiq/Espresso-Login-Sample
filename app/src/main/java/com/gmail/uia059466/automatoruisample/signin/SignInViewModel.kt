package com.gmail.uia059466.automatoruisample.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.uia059466.automatoruisample.data.FieldState
import com.gmail.uia059466.automatoruisample.data.ResultRequest
import com.gmail.uia059466.automatoruisample.data.UserRepository
import com.gmail.uia059466.automatoruisample.util.SingleLiveEvent
import com.gmail.uia059466.automatoruisample.util.checkEmail
import com.gmail.uia059466.automatoruisample.util.checkPassword

class SignInViewModel(private val repository: UserRepository) : ViewModel() {
  
  private val _state = MutableLiveData<SignInUiState>()
  val state: LiveData<SignInUiState> = _state
  
  val isLoading   : SingleLiveEvent<Boolean> = SingleLiveEvent<Boolean>()
  
  val snackbarText: SingleLiveEvent<Int> =  SingleLiveEvent<Int>()

  val navigateToAccount: SingleLiveEvent<Boolean> =   SingleLiveEvent<Boolean>()
  
  fun validateAndSignInUser(email: String, password: String) {
    val emailState = checkEmail(email)
    val passwordState = checkPassword(password)
    
    if (emailState is FieldState.Valid && passwordState is FieldState.Valid) {
      login(email, password)
      
    } else {
      isLoading.postValue(false)
      _state.value = SignInUiState(emailState, passwordState)
    }
  }
  
  private fun login(email: String, password: String) {
    val result = repository.signInWithEmail(email, password)
    when (result) {
      is ResultRequest.Success -> navigateToAccount.postValue(true)
      is ResultRequest.Error   -> snackbarText.postValue(result.resError)
    }
  }
  
  fun emailChanged(text: String) {
    val emailState = checkEmail(text)
    val passwordState = FieldState.Empty
    _state.value = SignInUiState(emailState, passwordState)
  }
  
  fun passwordChanged(password: String) {
    val emailState = FieldState.Empty
    val passwordState = checkPassword(password)
    _state.value = SignInUiState(emailState, passwordState)
  }
}