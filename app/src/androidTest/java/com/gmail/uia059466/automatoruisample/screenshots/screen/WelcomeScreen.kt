package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
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

class WelcomeScreen(on: UiDevice) : BaseScreen(on) {
    
    override val contentLayout: Int=R.id.welcome_layout
    
    private val title=R.id.title_tv
    private val themeSelectButton=R.id.theme_select_ll
    private val signUpButton=R.id.sign_up_btn
    private val singInWithEmail=R.id.sign_in_email_ll
    
    override fun checkDefaultLayout() {
        checkViewDisplayed(title)
        checkTextDisplayed(R.string.welcome_app_name)
        
        checkViewDisplayed(themeSelectButton)
        checkTextDisplayed(R.string.welcome_select_theme_label)
        
        checkViewDisplayed(signUpButton)
        checkTextDisplayed(R.string.welcome_sign_up_button)
        
        checkViewDisplayed(singInWithEmail)
    }

    fun selectTheme(){
        Espresso.onView(ViewMatchers.withId(themeSelectButton)).perform(ViewActions.click())
        val new=SelectThemeScreen(device)
        new.waitForScreenToBeDisplayed()
    }
    
    fun signUp(): SignUpScreen {
        Espresso.onView(ViewMatchers.withId(signUpButton)).perform(ViewActions.click())
        val new=SignUpScreen(device)
        new.waitForScreenToBeDisplayed()
        return new
    }
    
    fun signInWithEmail(): SignInScreen {
        Espresso.onView(ViewMatchers.withId(singInWithEmail)).perform(ViewActions.click())
        val new=SignInScreen(device)
        new.waitForScreenToBeDisplayed()
        return new
    }
}