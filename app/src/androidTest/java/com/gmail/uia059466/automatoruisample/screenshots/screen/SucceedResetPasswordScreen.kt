package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.uiautomator.UiDevice
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseScreen
import org.hamcrest.CoreMatchers.allOf

class SucceedResetPasswordScreen(on: UiDevice) : BaseScreen(on) {
    
    override val contentLayout: Int=0
    
    private val titleString=R.string.title_confirm_recover_password
    private val message=R.string.confirm_recovery_body
    private val okButton=android.R.string.ok

    fun pressOk() {
        Espresso.onView(ViewMatchers.withText(okButton)).perform(ViewActions.click())
        SignInScreen(device).waitForScreenToBeDisplayed()
    }
    
    fun waitForDialogToBeDisplayed() {
        waitForViewToBeDisplayed(ViewMatchers.withText(message), 3_000)
    }
    
    override fun checkDefaultLayout() {
        checkTextDisplayed(titleString)
        checkTextDisplayed(message)
        checkTextDisplayed(okButton)
    }
}