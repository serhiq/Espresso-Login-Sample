package com.gmail.uia059466.automatoruisample.screenshots.feat

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.screenshots.BaseTest
import com.gmail.uia059466.automatoruisample.screenshots.ui.RotoDevice
import junit.framework.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//  Happy patch

class FeatureTest : BaseTest() {
  
  val email="test@test.com"
  val password= "123456"
  
  val tempEmail="testI@test.com"
  
  @Test
  fun canLoginUsingEmail() {
    val d= RotoDevice(device)
    screenShot("welcome")
    
    val s=d.openWelcome().signInWithEmail()
    screenShot("sign_in_empty")
  
    s.inputEmail(email)
    s.inputPassword(password)
    screenShot("sign_in_email_fill")
  
    val a=s.enter()
    screenShot("account_after_sign_in")
  
    d.checkOpen(RotoDevice.type.ACCOUNT)
    assertTrue(a.emailOnScreen==email)
  }
  
  @Test
  fun canSignOut() {
    val d= RotoDevice(device)
    screenShot("Welcome")
    
    val s=d.openWelcome().signInWithEmail()
    screenShot("SignInEmail_empty")
    
    s.inputEmail(email)
    s.inputPassword(password)
    screenShot("SignInEmail_fill")
    
    val accountScreen=s.enter()
    screenShot("Account_after_sign_in")
    
    d.checkOpen(RotoDevice.type.ACCOUNT)
    assertTrue(accountScreen.emailOnScreen==email)
  
    val signOutDialog=accountScreen.signOut()
    screenShot("sign_out_dialog")
    signOutDialog.tapOk()
    
    d.checkOpen(RotoDevice.type.WELCOME)
    screenShot("Welcome")
  }
  
  @Test
  fun canRegisterAccount() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signUp()
    screenShot("SignUp_Empty")
  
    s.inputEmail   (tempEmail)
    s.inputPassword(password)
    screenShot("SignUp_fill")
  
    s.createAccount()
    screenShot("Account_after_signup")
  
    d.checkOpen(RotoDevice.type.ACCOUNT)
  }

  @Test
  fun canRemoveAccount() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    screenShot("SignIn")
    
    s.inputEmail   (tempEmail)
    s.inputPassword(password)
    screenShot("SignIn_fill")
    
    val accountScreen=s.enter()
    screenShot("Account_after_signIn")
    d.checkOpen(RotoDevice.type.ACCOUNT)
    
    val dialog=accountScreen.displayDeleteDialog()
    screenShot("delete_dialog")
    
    val passwordDialog= dialog.delete()
    screenShot("password_dialog")
    passwordDialog.inputAndTapOk(password)
  
    d.checkOpen(RotoDevice.type.WELCOME)
    screenShot("welcome")
  }
  
  @Test
  fun canRestorePassword() {
    val d=RotoDevice(device)
    val s=d.openWelcome().signInWithEmail()
    screenShot("SignIn")
    
    val resetScreen=s.navigateToResetPassword()
    
    screenShot("ResetPassword_empty")
    
    resetScreen.inputEmail(email)
    screenShot("ResetPassword_fill")
    
    val sussedDialog=resetScreen.send()
    screenShot("succeed_dialog")
    
    sussedDialog.pressOk()
    d.checkOpen(RotoDevice.type.SIGN_IN)
    screenShot("sign_in")
  }
}