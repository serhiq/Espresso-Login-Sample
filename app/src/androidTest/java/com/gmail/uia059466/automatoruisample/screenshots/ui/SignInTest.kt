package com.gmail.uia059466.automatoruisample.screenshots.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseTest
import junit.framework.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class SignInTest : BaseTest() {
  val email="test@test.com"
  val password= "123456"

  @Test
  fun signIn_success() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
  
    s.inputEmail(email)
    s.inputPassword(password)
    val a=s.enter()
    
    d.checkOpen(RotoDevice.type.ACCOUNT)
    
    assertTrue(a.emailOnScreen==email)
  }
  
  
  @Test
  fun signIn_missingEmail_error() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
  
    s.inputEmail("")
    s.inputPassword("123456")
    s.enter()
    sleep(400)
    
    s.checkHintEmail(R.string.missing_email_address)
    d.checkOpen(RotoDevice.type.SIGN_IN)
  }
  
  @Test
  fun singIn_missingPassword_error() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
  
    s.inputPassword("")
    s.enter()
    s.checkHintPassword(R.string.missing_password)
  
    d.checkOpen(RotoDevice.type.SIGN_IN)
  }
  
  @Test
  fun signIn_openResetPassword_success() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    s.navigateToResetPassword()
    
    d.checkOpen(RotoDevice.type.RESET_PASSWORD)
  }
  
  @Test
  fun signIn_openSignUp_success() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    s.navigateToSignUp()
    
    d.checkOpen(RotoDevice.type.SIGN_UP)
  }
  
  @Test
  fun sign_in_basicViewsDisplayed(){
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    d.checkOpen(RotoDevice.type.SIGN_IN)
    s.checkDefaultLayout()
  }
  
  @Test
  fun cantSignInMissingSymbolEmail() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    
    s.inputEmail("sdfsdf.com")
    s.enter()
    
    s.checkHintEmail(R.string.invalid_email)
    d.checkOpen(RotoDevice.type.SIGN_IN)
  }
  
  @Test
  fun cantSignInSmallPassword() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    
    s.inputPassword("s")
    s.enter()
    
    s.checkHintPassword(R.string.invalid_password)
    d.checkOpen(RotoDevice.type.SIGN_IN)
  }
  
  fun whenEmailNotExist_displaySnackBar(){
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    s.inputEmail("df@test.com")
    s.inputPassword("123456")
    s.enter()
    s.checkSnackBar(R.string.email_address_no_exist)
  }
}