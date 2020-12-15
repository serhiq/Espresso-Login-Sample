package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.uiautomator.UiDevice
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseScreen
import com.gmail.uia059466.automatoruisample.screenshots.hasTextInputLayoutHintText

class ResetPasswordScreen(on: UiDevice) : BaseScreen(on) {
    
    override val contentLayout: Int=R.id.reset_password_ll
    
    private val description=R.string.reset_password_body
    private val emailEt=R.id.email
    private val emailLayout=R.id.email_layout
    private val sendButton=R.id.send_button
    
    fun inputEmail(email: String) {
        onView(withId(emailEt)).perform(
                ViewActions.replaceText(email),
                ViewActions.closeSoftKeyboard()
                                       )
    }
    
    fun send(): SucceedResetPasswordScreen {
        Espresso.onView(ViewMatchers.withId(sendButton)).perform(ViewActions.click())
        val new=SucceedResetPasswordScreen(device)
        new.waitForDialogToBeDisplayed()
        return new
    }
    
    fun resetPassword(email: String){
        inputEmail(email)
        send()
    }
    
    
    fun checkHintEmail(@StringRes error:  Int) {
        onView(withId(emailLayout)).check(
                ViewAssertions.matches(hasTextInputLayoutHintText(string(error)))
                                        )
    }
    
    
    fun checkSnackBar(@StringRes error:  Int) {
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(error)))
    }
    override fun checkDefaultLayout(){
        onView(withText(description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(contentLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(emailEt)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(sendButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
