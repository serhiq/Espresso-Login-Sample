package com.gmail.uia059466.automatoruisample.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.data.FieldState
import com.gmail.uia059466.automatoruisample.data.ResultRequest
import com.gmail.uia059466.automatoruisample.data.UserRepository
import com.gmail.uia059466.automatoruisample.getOrAwaitValue
import com.gmail.uia059466.automatoruisample.signin.SignInUiState
import com.gmail.uia059466.automatoruisample.signin.SignInViewModel
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
class SignInViewModelTest {

  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()
  
  val email = "test@test.com"
  val password = "sdfsdf"
  
  @Mock
  lateinit var repository: UserRepository
  
  lateinit var viewModel: SignInViewModel
  
  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    viewModel = SignInViewModel(repository)
  }
  
  @Test
  fun login_whenUserLoggedInSuccessfully() {
    val resultRequest = ResultRequest.Success<String>(email)
    
    Mockito.`when`(repository.signInWithEmail(email, password)).thenReturn(resultRequest)
    
    viewModel.validateAndSignInUser(email, password)
    
    assertTrue(viewModel.navigateToAccount.getOrAwaitValue())
  }
  
  @Test
  fun login_whenUserLogInFailed() {
    val resultRequest = ResultRequest.Error(R.string.email_address_no_exist)
    
    Mockito.`when`(repository.signInWithEmail(email, password)).thenReturn(resultRequest)
    
    viewModel.validateAndSignInUser(email, password)
    
    assertTrue(viewModel.snackbarText.getOrAwaitValue() == R.string.email_address_no_exist)
  }
  
  @Test
  fun loginDataChanged_withInvalidPassword() {
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
  fun loginDataChanged_withInvalidEmail() {
    viewModel.emailChanged("df")
    
    val expected =
            SignInUiState(
                    emailState = FieldState.Invalid(R.string.invalid_email),
                    passwordState = FieldState.Empty
                         )
    val actual = viewModel.state.getOrAwaitValue()
    
    assertEquals(expected, actual)
  }
  
  @Test
  fun loginDataChanged_withEmptyEmail() {
    viewModel.passwordChanged("123456")
    val expected = SignInUiState(emailState = FieldState.Empty, passwordState = FieldState.Valid)
    val actual = viewModel.state.getOrAwaitValue()
    
    assertEquals(expected, actual)
  }
  
  @Test
  fun loginDataChanged_withPasswordEmail() {
    viewModel.emailChanged("test@test.com")
    val expected = SignInUiState(emailState = FieldState.Valid, passwordState = FieldState.Empty)
    val actual = viewModel.state.getOrAwaitValue()
    
    assertEquals(expected, actual)
  }
}