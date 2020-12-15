package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.uiautomator.UiDevice
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseScreen

class SignOutDialogScreen(on: UiDevice) : BaseScreen(on) {
//   it is dialog without layout no content layout
  override val contentLayout: Int=0
  
  private val title= R.string.dialog_logout_title
  private val message= R.string.dialog_logout_message
  private val confirmButtonText= R.string.dialog_logout_button
  
  fun waitForDialogToBeDisplayed(){
    waitForViewToBeDisplayed(ViewMatchers.withText(message),3_000)
  }
  
  fun tapOk() {
    Espresso.onView(ViewMatchers.withText(confirmButtonText)).perform(ViewActions.click())
    WelcomeScreen(device).waitForScreenToBeDisplayed()
  }
  
  override fun checkDefaultLayout() {
    checkTextDisplayed(title)
    checkTextDisplayed(message)
    checkTextDisplayed(confirmButtonText)
  }
}