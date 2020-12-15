package com.gmail.uia059466.automatoruisample.screenshots.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class ResetPassword : BaseTest() {
  val email="test@test.com"
  
  @Test
  fun canResetPassword() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    val resetScreen=s.navigateToResetPassword()
    resetScreen.inputEmail(email)
    val next=resetScreen.send()
     next.pressOk()
    d.checkOpen(RotoDevice.type.SIGN_IN)
  }
  
  @Test
  fun when_email_no_correct_expect_hint() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    val reset=s.navigateToResetPassword()
    reset.inputEmail("df")
    reset.send()
    reset.checkHintEmail(R.string.invalid_email)
    d.checkOpen(RotoDevice.type.RESET_PASSWORD)
  }
  
  @Test
  fun when_back_displaySignIn() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    s.navigateToResetPassword()
    device.pressBack()
    
    d.checkOpen(RotoDevice.type.SIGN_IN)
  }
  
  @Test
  fun resetPassword_basicViewsDisplayed(){
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    val resetPassword=s.navigateToResetPassword()
    resetPassword.checkDefaultLayout()
    
    d.checkOpen(RotoDevice.type.RESET_PASSWORD)
  }
  
  @Test
  fun whenEmailNotExist_displaySnackBar(){
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    val reset=s.navigateToResetPassword()
    d.checkOpen(RotoDevice.type.RESET_PASSWORD)
    reset.inputEmail("df@test.com")
    reset.send()
    reset.checkSnackBar(R.string.email_address_no_exist_reset)
  }
}