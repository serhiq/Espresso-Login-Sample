package com.gmail.uia059466.automatoruisample.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.account.AccountViewModel
import com.gmail.uia059466.automatoruisample.data.ResultRequest
import com.gmail.uia059466.automatoruisample.data.UserRepository
import com.gmail.uia059466.automatoruisample.getOrAwaitValue
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class AccountViewModelTest {
  
  @get:Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()
  
  @Mock
  lateinit var repository: UserRepository
  
  lateinit var viewModel: AccountViewModel
  
  private val email = "test@test.com"
  private val password = "sdfsdf"
  
  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    viewModel = AccountViewModel(repository)
  }
  
  @Test
  fun whenDeleteSucceed_expectNavigateToWelcome() {
    val resultRequest = ResultRequest.Success<String>(email)
    
    Mockito.`when`(repository.delete(email, password)).thenReturn(resultRequest)
    
    viewModel.deleteUser(email, password)
    
    assertTrue(viewModel.navigateToWelcome.getOrAwaitValue())
  }
  
  @Test
  fun whenDeleteFailed_expectDisplaySnackbar() {
    val resultRequest = ResultRequest.Error(R.string.email_address_no_exist)
    Mockito.`when`(repository.delete(email, password)).thenReturn(resultRequest)
  
    viewModel.deleteUser(email, password)
  
    assertTrue(viewModel.snackbarText.getOrAwaitValue() == R.string.email_address_no_exist)
  }
  
  @Test
  fun whenSignOutSucceed_expectNavigateToWelcome() {
    val resultRequest = ResultRequest.Success<String>(email)
    
    Mockito.`when`(repository.signOut()).thenReturn(resultRequest)
    
    viewModel.signOut()
    
    assertTrue(viewModel.navigateToWelcome.getOrAwaitValue())
  }
  
  @Test
  fun whenSignOutFailed_expectDisplaySnackbar() {
    val resultRequest = ResultRequest.Error(R.string.email_address_no_exist)
    Mockito.`when`(repository.signOut()).thenReturn(resultRequest)
    
    viewModel.signOut()
    
    assertTrue(viewModel.snackbarText.getOrAwaitValue() == R.string.email_address_no_exist)
  }
}