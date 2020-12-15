package com.gmail.uia059466.automatoruisample.data

interface UserRepository {
  
  fun signInWithEmail(email: String, password: String): ResultRequest<String>
  fun delete         (email: String, password: String): ResultRequest<String>
  fun signOut        (): ResultRequest<String>
  fun createAccountWithEmail(email: String, password: String): ResultRequest<String>
  fun resetPassword         (email: String): ResultRequest<String>
  fun requestCurrentUser     (): ResultRequest<String>
}
  
