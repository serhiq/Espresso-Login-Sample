package com.gmail.uia059466.automatoruisample.screenshots.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import com.gmail.uia059466.automatoruisample.screenshots.BaseTest
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ErrorCollector
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WelcomeTest : BaseTest() {
  @get:Rule
  var collector = ErrorCollector()
  
  @Test
  fun canOpenRegister(){
    val d=RotoDevice(device)
    val s=d.openWelcome()
    
    s.signUp()
    
    d.checkOpen(RotoDevice.type.SIGN_UP)
  }
  
  @Test
  fun canOpenLogin() {
    val d=RotoDevice(device)
    val s=d.openWelcome()
  
    s.signInWithEmail()
  
    d.checkOpen(RotoDevice.type.SIGN_IN)
  }
  
  @Test
  fun canOpenThemeDialog() {
    val d=RotoDevice(device)
    val s=d.openWelcome()
  
    s.selectTheme()
  
    d.checkOpen(RotoDevice.type.DIALOG_SELECT_THEME)
  }
  
  @Test
  fun onBackPressedBack() {
    device.pressBack()
    // Wait for launcher
    val launcherPackage: String = getLauncherPackageName()
    Assert.assertThat(launcherPackage, CoreMatchers.notNullValue())
    device.wait<Boolean>(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            500
                        )
  }
  
  @Test
  fun welcome_basicViewsDisplayed(){
    val d=RotoDevice(device)
    val s=d.openWelcome()
    d.checkOpen(RotoDevice.type.WELCOME)
    s.checkDefaultLayout()
  }
}