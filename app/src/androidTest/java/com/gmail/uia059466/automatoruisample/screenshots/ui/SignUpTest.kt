package com.gmail.uia059466.automatoruisample.screenshots.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseTest
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class SignUpTest : BaseTest() {
  val email="test63@test.com"
  val password= "123456"
  
  @Test
  fun canRegister() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signUp()
  
    s.inputEmail(email)
    s.inputPassword(password)

    val a=s.createAccount()
    d.checkOpen(RotoDevice.type.ACCOUNT)

    assertTrue(a.emailOnScreen==email)
  }
  
  @Test
  fun cantRegisterMissingEmail() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signUp()
  
    s.inputEmail("")
    s.createAccount()
    
    s.checkHintEmail(R.string.missing_email_address)
    d.checkOpen(RotoDevice.type.SIGN_UP)
  }
  
  @Test
  fun cantRegisterMissingSymbolEmail() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signUp()
    
    s.inputEmail("sdfsdf.com")
    s.createAccount()
    
    s.checkHintEmail(R.string.invalid_email)
    d.checkOpen(RotoDevice.type.SIGN_UP)
  }
  
  @Test
  fun cantRegisterSmallPassword() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signUp()
    
    s.inputPassword("s")
    s.createAccount()
    
    s.checkHintPassword(R.string.invalid_password)
    d.checkOpen(RotoDevice.type.SIGN_UP)
  }
  @Test
  fun cantRegisterMissingPassword() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signUp()
  
    s.inputPassword("")
    s.createAccount()
    s.checkHintPassword(R.string.missing_password)
  
    d.checkOpen(RotoDevice.type.SIGN_UP)
  }
  
  @Test
  fun sign_up_basicViewsDisplayed(){
    val d=RotoDevice(device)
    val s=d.openWelcome().signUp()
    d.checkOpen(RotoDevice.type.SIGN_UP)
    s.checkDefaultLayout()
  }
}