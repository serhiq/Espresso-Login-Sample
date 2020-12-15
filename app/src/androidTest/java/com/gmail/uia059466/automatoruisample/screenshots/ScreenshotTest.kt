package com.gmail.uia059466.automatoruisample.screenshots

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.screenshots.screen.SelectThemeScreen
import com.gmail.uia059466.automatoruisample.screenshots.screen.WelcomeScreen
import com.gmail.uia059466.automatoruisample.screenshots.ui.RotoDevice
import com.gmail.uia059466.automatoruisample.themes.Theme
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScreenshotTest : BaseTest() {
  
  val email = "test@test.com"
  val password = "123456"
  
    @Test
  fun changeThemeToLight() {
     checkTheme(Theme.LIGHT)
  }
   
   @Test
   fun changeThemeToDark() {
      checkTheme(Theme.DARK)
   }
   
   @Test
   fun changeThemeToMint() {
      checkTheme(Theme.MINT)
   }
   
   @Test
   fun changeThemeToGray() {
      checkTheme(Theme.GRAY)
   }
   
   private fun checkTheme(theme: Theme){
      selectTheme(theme)
      screenShot(theme.rawValue)
      
   }
   
   private fun selectTheme(theme: Theme) {
      val welcome = WelcomeScreen(on = device)
      welcome.selectTheme()
      
      val themeDialog= SelectThemeScreen(device)
      themeDialog.selectTheme(theme)
      sleep(300)
      screenShootTest()
   }
   
   private fun screenShootTest(){
     signUpAndDelete()
     signInSignOut()
     resetPassword()
  }
private  fun signUpAndDelete() {
    val d = RotoDevice(device)
    val welcome = d.openWelcome()
    
    val signUp = welcome.signUp()
    screenShot("sign_up_empty")
    
    signUp.inputEmail("ee")
    sleep(300)
    signUp.createAccount()
    screenShot("sign_up_error")
    
    val rEmail = "emrii@eima.com"
    val rPassword = "uhnzaq3"
    signUp.inputEmail(rEmail)
    signUp.inputPassword(rPassword)
    sleep(300)
    screenShot("sign_up_fill")
    
    val account = signUp.createAccount()
    screenShot("account_after_sign_up")
    
    val deleteDialog = account.displayDeleteDialog()
    screenShot("dialog_delete")
    
    val passwordInputDialog = deleteDialog.delete()
    screenShot("dialog_password_input")
    
    passwordInputDialog.inputAndTapOk(rPassword)
    sleep(500)
    screenShot("welcome_after_delete")
  }
  
  fun signInSignOut() {
    val d = RotoDevice(device)
    screenShot("welcome")
    
    val welcome = d.openWelcome()
    val s = welcome.signInWithEmail()
    screenShot("sign_in_empty")
    
    s.inputEmail("aa")
    sleep(300)
    s.enter()
    screenShot("sign_in_error_empty")
    
    s.inputEmail(email)
    s.inputPassword(password)
    sleep(300)
    screenShot("sign_in_email_fill")
    
    val a = s.enter()
    screenShot("account_after_sign_in")
    
    val dialog = a.signOut()
    screenShot("dialog_sign_out")
    
    dialog.tapOk()
    screenShot("welcome_after_sign_out")
    
    
  }

  private fun resetPassword() {
    val d = RotoDevice(device)
    screenShot("welcome")
    
    val welcome = d.openWelcome()
    val resetPassword = welcome.signInWithEmail().navigateToResetPassword()
    screenShot("reset_password_empty")
    
    resetPassword.inputEmail("sdf")
    resetPassword.send()
    screenShot("reset_password_error")
    
    resetPassword.inputEmail(email)
    screenShot("reset_password_fill")
    
    val dialog_sussed = resetPassword.send()
    screenShot("dialog_reset_password_succed")
    dialog_sussed.pressOk()
    
    screenShot("sign_in_after_reset")
  }
}