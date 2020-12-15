package com.gmail.uia059466.automatoruisample.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.data.FieldState
import com.gmail.uia059466.automatoruisample.data.ResultRequest
import com.gmail.uia059466.automatoruisample.data.UserRepository
import com.gmail.uia059466.automatoruisample.getOrAwaitValue
import com.gmail.uia059466.automatoruisample.signin.SignInUiState
import com.gmail.uia059466.automatoruisample.signup.SignUpViewModel
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
class SignUpViewModelTest {
  
  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()
  
  val email = "test@test.com"
  val password = "sdfsdf"
  
  @Mock
  lateinit var repository: UserRepository
  
  lateinit var viewModel: SignUpViewModel
  
  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    viewModel = SignUpViewModel(repository)
  }
  
  @Test
  fun whenSignUpSucceed_expectNavigateToAccount() {
    val resultRequest = ResultRequest.Success<String>(email)
    
    Mockito.`when`(repository.createAccountWithEmail(email, password)).thenReturn(resultRequest)
    
    viewModel.validateAndSignUpUser(email, password)
    
    assertTrue(viewModel.navigateToAccount.getOrAwaitValue())
  }
  
  @Test
  fun whenSignUpFailed_expectSnackbar() {
    val resultRequest = ResultRequest.Error(R.string.email_address_exist)
    
    Mockito.`when`(repository.createAccountWithEmail(email, password)).thenReturn(resultRequest)
    
    viewModel.validateAndSignUpUser(email, password)
    
    assertTrue(viewModel.snackbarText.getOrAwaitValue() == R.string.email_address_exist)
  }
  
  @Test
  fun whenIncorrectPassword_expectInvalidState() {
    viewModel.passwordChanged("df")
    
    val expected =
            SignInUiState(
                    emailState = FieldState.Empty,
                    passwordState = FieldState.Invalid(R.string.invalid_password)
                         )
    val actual = viewModel.state.getOrAwaitValue()
    
    assertEquals(expected.emailState, actual.emailState)
  }
  
  @Test
  fun whenIncorrectEmail_expectInvalidState() {
    viewModel.emailChanged("df")
    
    val expected =
            SignInUiState(
                    emailState = FieldState.Invalid(R.string.invalid_email),
                    passwordState = FieldState.Empty
                         )
    val actual = viewModel.state.getOrAwaitValue()
    
    assertEquals(expected.passwordState, actual.passwordState)
    assertEquals(expected.emailState, actual.emailState)
  }
  
  @Test
  fun whenMissingEmail_expectInvalidState() {
    viewModel.passwordChanged("123456")
    val expected = SignInUiState(emailState = FieldState.Empty, passwordState = FieldState.Valid)
    val actual = viewModel.state.getOrAwaitValue()
  
    assertEquals(expected.passwordState, actual.passwordState)
    assertEquals(expected.emailState, actual.emailState)
  }
  
  @Test
  fun whenMissingPassword_expectInvalidState() {
    viewModel.emailChanged("test@test.com")
    val expected = SignInUiState(emailState = FieldState.Valid, passwordState = FieldState.Empty)
    val actual = viewModel.state.getOrAwaitValue()
  
    assertEquals(expected.passwordState, actual.passwordState)
    assertEquals(expected.emailState, actual.emailState)
  }
}