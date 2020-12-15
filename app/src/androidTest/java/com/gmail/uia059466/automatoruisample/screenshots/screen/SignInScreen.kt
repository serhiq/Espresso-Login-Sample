package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.By
import androidx.test.uiautomator.SearchCondition
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseScreen
import com.gmail.uia059466.automatoruisample.screenshots.hasTextInputLayoutHintText

class SignInScreen(on: UiDevice) : BaseScreen(on) {
    
    override val contentLayout: Int=R.id.email_sign_in_layout
    
    private val enterButton=R.id.button_enter
    private val emailEt=R.id.email
    private val emailTl=R.id.email_layout
    private val passwordTl=R.id.password_layout
    private val passwordEt=R.id.password
    private val resetPasswordTv=R.id.password_reset_tv
    private val signInTv=R.id.create_account_tv
    
    fun inputEmail(email: String) {
        onView(withId(emailEt)).perform(
                ViewActions.replaceText(email),
                ViewActions.closeSoftKeyboard()
                                       )
         }
    
    fun enter(): AccountScreen {
        Espresso.onView(ViewMatchers.withId(enterButton)).perform(ViewActions.click())
        val new=AccountScreen(device)
        new.waitForScreenToBeDisplayed()
        return new
    }
    
    fun inputPassword(password: String) {
        onView(withId(passwordEt)).perform(
                ViewActions.replaceText(password),
                ViewActions.closeSoftKeyboard())
    }
    
    fun loginAs(email: String, password: String): AccountScreen {
        inputEmail(email)
        inputPassword(password)
        return enter()
    }
    
    fun navigateToResetPassword(): ResetPasswordScreen {
        Espresso.onView(ViewMatchers.withId(resetPasswordTv)).perform(ViewActions.click())
        val new=ResetPasswordScreen(device)
        new.waitForScreenToBeDisplayed()
        return new
    }
    
    fun navigateToSignUp(): SignUpScreen {
        Espresso.onView(ViewMatchers.withId(signInTv)).perform(ViewActions.click())
        val new=SignUpScreen(device)
        new.waitForScreenToBeDisplayed()
        return new
    }

    fun checkHintEmail(@StringRes error:  Int) {
        onView(withId(emailTl)).check(
        matches(hasTextInputLayoutHintText(string(error))))
    }
    
    fun checkHintPassword(@StringRes error:  Int) {
        onView(withId(passwordTl)).check(
                matches(hasTextInputLayoutHintText(string(error))))
    }
    
    fun checkSnackBar(@StringRes error:  Int) {
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(ViewMatchers.withText(error)))
    }
    override fun checkDefaultLayout() {
        checkViewDisplayed(contentLayout)
        checkViewDisplayed(enterButton)
        checkViewDisplayed(emailEt)
        checkViewDisplayed(passwordEt)
        checkViewDisplayed(resetPasswordTv)
        checkViewDisplayed(signInTv)
    }
}
