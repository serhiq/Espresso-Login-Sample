package com.gmail.uia059466.automatoruisample.data

import com.gmail.uia059466.automatoruisample.R

class LoginRepositoryImpl : UserRepository {
  
  var map = HashMap<String, String>()
  var currentUser = ""
  
  init {
    val email = "test@test.com"
    val password = "123456"
    map.put(email, password)
  }
  
  override fun signInWithEmail(email: String, password: String): ResultRequest<String> {
    val error= R.string.email_address_no_exist
    val unknownError= R.string.email_sign_in_error
  
    if (!map.containsKey(email)) ResultRequest.Error(error)
    val passwordFromMap = map.get(email)
    return when {
      passwordFromMap != null && password == passwordFromMap -> {
        currentUser = email;
        ResultRequest.Success(email)
      }
      else -> ResultRequest.Error(unknownError)
    }
  }
  
  override fun delete(email: String, password: String): ResultRequest<String> {
    val error= R.string.email_address_no_exist
    val unknownError= R.string.error_unknown
  
    if (!map.containsKey(email)) ResultRequest.Error(error)
    val passwordFromMap = map.get(email)
    return when {
      passwordFromMap != null && password == passwordFromMap -> {
       map.remove(email); ResultRequest.Success(email); }
      else -> ResultRequest.Error(unknownError)
    }
  }
  
  override fun signOut(): ResultRequest<String> {
    currentUser = ""
    return ResultRequest.Success("")
  }
  
  override fun createAccountWithEmail(email: String, password: String): ResultRequest<String> {
    val error= R.string.email_address_exist
  
    if (map.containsKey(email)) return ResultRequest.Error(error)
    map.put(email, password)
    currentUser = email
    return ResultRequest.Success(email)
  }
  
  override fun resetPassword(email: String): ResultRequest<String> {
    val error= R.string.email_address_no_exist_reset
    
    if (!map.containsKey(email)) return ResultRequest.Error(error)
    return ResultRequest.Success(email)
  }
  
  override fun requestCurrentUser(): ResultRequest<String> {
    return ResultRequest.Success(currentUser)
  }
  
  companion object {
    
    private var INSTANCE: LoginRepositoryImpl? = null
    
    fun getInstance(): UserRepository {
      synchronized(this) {
        var instance = INSTANCE
        if (instance == null) {
          instance = LoginRepositoryImpl()
          INSTANCE = instance
        }
        return instance
      }
    }
  }
}