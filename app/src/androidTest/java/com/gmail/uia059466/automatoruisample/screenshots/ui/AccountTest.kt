package com.gmail.uia059466.automatoruisample.screenshots.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gmail.uia059466.automatoruisample.screenshots.BaseTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccountTest : BaseTest() {
  
  val email = "test@test.com"
  val password = "123456"
  
  @Test
  fun canOpenDelete() {
    val d = RotoDevice(device)
    val s = d.openWelcome().signInWithEmail().loginAs(email, password)
    
    val dialog = s.displayDeleteDialog()
    dialog.waitForDialogToBeDisplayed()
  }
  
  @Test
  fun canOpenSignOut() {
    val d = RotoDevice(device)
    val s = d.openWelcome().signInWithEmail().loginAs(email, password)
    
    val newS = s.signOut()
    newS.waitForDialogToBeDisplayed()
  }
  
  @Test
  fun onBack() {
    val d = RotoDevice(device)
    
    d.openWelcome().signInWithEmail().loginAs(email, password)
    device.pressBack()
    
    d.checkOpen(RotoDevice.type.WELCOME)
  }
  
  @Test
  fun account_basicViewsDisplayed() {
    val d = RotoDevice(device)
    val s = d.openWelcome().signInWithEmail().loginAs(email, password)
    d.checkOpen(RotoDevice.type.ACCOUNT)
    s.checkDefaultLayout()
  }
}