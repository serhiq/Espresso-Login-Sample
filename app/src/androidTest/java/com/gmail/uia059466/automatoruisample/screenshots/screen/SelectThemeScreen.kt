package com.gmail.uia059466.automatoruisample.screenshots.screen

import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.screenshots.BaseScreen
import com.gmail.uia059466.automatoruisample.themes.Theme
import junit.framework.Assert.assertTrue

class SelectThemeScreen(on: UiDevice) : BaseScreen(on) {
  
  override val contentLayout: Int = R.id.select_dialog_listview
 
  private val title= R.string.dialog_select_themes_title
  private val listView = on.findObject(UiSelector().resourceId("$id/select_dialog_listview"))
  
  private val numberOfTheme: Int
    get() {
      return listView.childCount
    }
  
  fun selectTheme(theme: Theme) {
    val themes = Theme.values()
    val indexTheme = themes.indexOf(theme)
    val itemTheme = listView.getChild(UiSelector().index(indexTheme))
    itemTheme.click()
  }
  
  override fun checkDefaultLayout() {
    checkTextDisplayed(title)
    assertTrue(numberOfTheme==Theme.values().size)
  }
}
