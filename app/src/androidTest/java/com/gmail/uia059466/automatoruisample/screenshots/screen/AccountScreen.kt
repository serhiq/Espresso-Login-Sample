package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.uiautomator.UiDevice
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseScreen
import com.gmail.uia059466.automatoruisample.screenshots.getTextFromTextView

class AccountScreen(on: UiDevice) : BaseScreen(on) {
    
    override val contentLayout: Int=R.id.signed_in_cl
    
    private val emailTv = R.id.description_tv
    private val deleteButton = R.id.delete_account_btn
    private val signOutButton = R.id.sign_out_btn
    
    val emailOnScreen: String
        get() = getText(emailTv)
    
    fun signOut(): SignOutDialogScreen {
        Espresso.onView(ViewMatchers.withId(signOutButton)).perform(ViewActions.click())
        return SignOutDialogScreen(device)
    }
    
    fun displayDeleteDialog(): DeleteDialogScreen {
        Espresso.onView(ViewMatchers.withId(deleteButton)).perform(ViewActions.click())
        return DeleteDialogScreen(device)
    }
    
    fun getText(id: Int): String {
        return getTextFromTextView(withId(id))
    }
    
    override fun checkDefaultLayout() {
        onView(withId(contentLayout)).check(matches(isDisplayed()))
        onView(withId(emailTv)).check(matches(isDisplayed()))
        onView(withId(deleteButton)).check(matches(isDisplayed()))
        onView(withId(signOutButton)).check(matches(isDisplayed()))
    }
}