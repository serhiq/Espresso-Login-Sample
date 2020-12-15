package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.uiautomator.UiDevice
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseScreen

class DeleteDialogScreen(on: UiDevice) : BaseScreen(on) {
  override val contentLayout: Int = 0
  
  private val title= R.string.dialog_delete_user_title
  private val message= R.string.dialog_delete_user_message
  private val confirmButtonText= R.string.dialog_delete_user_button
  
  fun delete(): InputDialogScreen {
    Espresso.onView(ViewMatchers.withText(confirmButtonText)).perform(ViewActions.click())
    return InputDialogScreen(device)
  }
  
  fun waitForDialogToBeDisplayed() {
    waitForViewToBeDisplayed(ViewMatchers.withText(message), 3_000)
  }
  
  override fun checkDefaultLayout() {
    checkTextDisplayed(title)
    checkTextDisplayed(message)
    checkTextDisplayed(confirmButtonText)
  }
}