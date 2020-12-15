package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.UiDevice
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseScreen
import com.gmail.uia059466.automatoruisample.screenshots.hasTextInputLayoutHintText

class SignUpScreen(on: UiDevice) : BaseScreen(on) {
    
    override val contentLayout: Int=R.id.sign_up_fragment_ll
    
    private val emailEt=R.id.email
    private val passwordEt=R.id.password
    private val createButton=R.id.create_btn
    private val privacyTextView=R.id.privacy_tv
 
    private val emailTl=R.id.email_layout
    private val passwordTl=R.id.password_layout
    
    fun inputEmail(email: String) {
        onView(withId(emailEt)).perform(
                ViewActions.replaceText(email),
                ViewActions.closeSoftKeyboard()
                                       )
    }
    
    fun createAccount(): AccountScreen {
        Espresso.onView(ViewMatchers.withId(createButton)).perform(ViewActions.click())
        val new=AccountScreen(device)
        new.waitForScreenToBeDisplayed()
        return new
    }
    
    fun inputPassword(password: String) {
        onView(withId(passwordEt)).perform(
                ViewActions.replaceText(password),
                ViewActions.closeSoftKeyboard())
    }
    
    fun loginAs(email: String, password: String){
        inputEmail(email)
        inputPassword(password)
        val acc=createAccount()
        acc.waitForScreenToBeDisplayed()
    }
    
    fun displayPrivacyPolicy() {
        Espresso.onView(ViewMatchers.withId(R.id.privacy_tv)).perform(ViewActions.click())
    }
    
    override fun checkDefaultLayout() {
        checkViewDisplayed(emailEt)
        checkViewDisplayed(passwordEt)
        checkViewDisplayed(createButton)
        checkViewDisplayed(privacyTextView)
    }
    
    fun checkHintEmail(@StringRes error:  Int) {
        onView(withId(emailTl)).check(
                ViewAssertions.matches(hasTextInputLayoutHintText(string(error)))
                                     )
    }
    
    fun checkHintPassword(@StringRes error:  Int) {
        onView(withId(passwordTl)).check(
                ViewAssertions.matches(hasTextInputLayoutHintText(string(error)))
                                        )
    }
}