package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.UiDevice
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseScreen

class InputDialogScreen(on: UiDevice) : BaseScreen(on) {
  
  private val okButtonText = android.R.string.ok
  private val passwordEt = R.id.edit_text
  override val contentLayout: Int = R.id.enter_password_fl
  
  fun inputAndTapOk(password: String) {
    inputPassword(password)
    tapOk()
  }
  
  fun inputPassword(password: String) {
    onView(withId(passwordEt)).perform(
            ViewActions.replaceText(password),
            ViewActions.closeSoftKeyboard()
                                      )
    
  }
  
  fun tapOk() {
    Espresso.onView(ViewMatchers.withText(okButtonText)).perform(ViewActions.click())
  }
  
  override fun checkDefaultLayout() {
    checkTextDisplayed(okButtonText)
    checkViewDisplayed(passwordEt)
  }
}
