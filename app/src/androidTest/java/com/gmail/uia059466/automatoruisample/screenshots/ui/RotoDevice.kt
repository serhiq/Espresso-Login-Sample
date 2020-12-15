package com.gmail.uia059466.automatoruisample.screenshots.ui

import androidx.test.uiautomator.UiDevice
import com.gmail.uia059466.automatoruisample.screenshots.screen.AccountScreen
import com.gmail.uia059466.automatoruisample.screenshots.screen.DeleteDialogScreen
import com.gmail.uia059466.automatoruisample.screenshots.screen.ResetPasswordScreen
import com.gmail.uia059466.automatoruisample.screenshots.screen.SelectThemeScreen
import com.gmail.uia059466.automatoruisample.screenshots.screen.SignInScreen
import com.gmail.uia059466.automatoruisample.screenshots.screen.SignOutDialogScreen
import com.gmail.uia059466.automatoruisample.screenshots.screen.SignUpScreen
import com.gmail.uia059466.automatoruisample.screenshots.screen.SucceedResetPasswordScreen
import com.gmail.uia059466.automatoruisample.screenshots.screen.WelcomeScreen
import junit.framework.Assert.assertTrue

class RotoDevice(val device: UiDevice) {
  
  fun openWelcome(): WelcomeScreen {
    return WelcomeScreen(on = device)
  }
  
  fun checkOpen(type: type) {
    val isDisplayed=when(type){
      RotoDevice.type.WELCOME ->WelcomeScreen(on = device).isShow()
      RotoDevice.type.SIGN_UP ->SignUpScreen(on = device).isShow()
      RotoDevice.type.SIGN_IN ->SignInScreen(on = device).isShow()
      RotoDevice.type.DIALOG_SELECT_THEME -> SelectThemeScreen(device).isShow()
      RotoDevice.type.ACCOUNT -> AccountScreen(device).isShow()
      RotoDevice.type.RESET_PASSWORD -> ResetPasswordScreen(device).isShow()
      RotoDevice.type.DIALOG_SUSSED_RESET -> SucceedResetPasswordScreen(device).isShow()
      RotoDevice.type.DIALOG_DELETE_ACCOUNT -> DeleteDialogScreen(device).isShow()
      RotoDevice.type.DIALOG_SIGN_OUT -> SignOutDialogScreen(device).isShow()
    }
    assertTrue(isDisplayed)
  }
  
  enum class type{
    WELCOME,SIGN_UP,SIGN_IN, DIALOG_SELECT_THEME, ACCOUNT, RESET_PASSWORD, DIALOG_SUSSED_RESET,
    DIALOG_DELETE_ACCOUNT,
    DIALOG_SIGN_OUT;
  }
}