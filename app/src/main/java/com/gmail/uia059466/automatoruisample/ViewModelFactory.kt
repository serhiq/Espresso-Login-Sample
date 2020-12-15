package com.gmail.uia059466.automatoruisample

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.uia059466.automatoruisample.account.AccountViewModel
import com.gmail.uia059466.automatoruisample.resetpassword.ResetPasswordViewModel
import com.gmail.uia059466.automatoruisample.signin.SignInViewModel
import com.gmail.uia059466.automatoruisample.signup.SignUpViewModel
import com.gmail.uia059466.automatoruisample.util.provideUserRepository

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
        private val a: Application
                                  ) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel> create(modelClass: Class<T>) =
    with(modelClass) {
      when {
        isAssignableFrom(SignInViewModel::class.java)            ->
          SignInViewModel(provideUserRepository())
        isAssignableFrom(SignUpViewModel::class.java)            ->
          SignUpViewModel(provideUserRepository())
        isAssignableFrom(ResetPasswordViewModel::class.java)            ->
          ResetPasswordViewModel(provideUserRepository())
        isAssignableFrom(AccountViewModel::class.java)            ->
          AccountViewModel(provideUserRepository())
         else                                              ->
          throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
      }
    } as T
}