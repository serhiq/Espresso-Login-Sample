package com.gmail.uia059466.automatoruisample.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.data.FieldState
import com.gmail.uia059466.automatoruisample.data.ResultRequest
import com.gmail.uia059466.automatoruisample.data.UserRepository
import com.gmail.uia059466.automatoruisample.getOrAwaitValue
import com.gmail.uia059466.automatoruisample.resetpassword.ResetPasswordViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class ResetPasswordViewModelTest {
 
  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()
  
  @Mock
  lateinit var repository: UserRepository
  
  lateinit var viewModel: ResetPasswordViewModel
  
  private val email = "test@test.com"
  
  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    viewModel = ResetPasswordViewModel(repository)
  }
  
  @Test
  fun whenResetSucceed_expectDisplayDialog() {
    val resultRequest = ResultRequest.Success<String>(email)
    
    Mockito.`when`(repository.resetPassword(email)).thenReturn(resultRequest)
    
    viewModel.validateAndReset(email)
    
    assertTrue(viewModel.showEmailSentDialog.getOrAwaitValue())
  }
  
  @Test
  fun whenResetFailed_expectDisplaySnackbar() {
    val resultRequest = ResultRequest.Error(R.string.email_address_no_exist)
    
    Mockito.`when`(repository.resetPassword(email)).thenReturn(resultRequest)
    
    viewModel.validateAndReset(email)
    
    assertTrue(viewModel.snackbarText.getOrAwaitValue() == R.string.email_address_no_exist)
  }
  
  @Test
  fun resetDataChanged_withInvalidEmail() {
   viewModel.emailChanged("df")
    
    val expected = FieldState.Invalid(R.string.invalid_email)
    
    val actual = viewModel.state.getOrAwaitValue()
    
    assertEquals(expected, actual)
  }
  
  @Test
  fun resetDataChanged_withEmptyEmail() {
    viewModel.emailChanged("")
    
    val expected = FieldState.Invalid(R.string.missing_email_address)
    
    val actual = viewModel.state.getOrAwaitValue()
  
    assertEquals(expected, actual)
  }
}