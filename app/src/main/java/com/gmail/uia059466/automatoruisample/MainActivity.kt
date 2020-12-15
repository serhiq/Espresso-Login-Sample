package com.gmail.uia059466.automatoruisample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.gmail.uia059466.automatoruisample.data.Prefs
import com.gmail.uia059466.automatoruisample.themes.SelectThemeDialogFragment
import com.gmail.uia059466.automatoruisample.themes.Theme
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
  
  lateinit var currentTheme: Theme
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    setupAppTheme()
  }
  
  private fun setupAppTheme() {
    val prefs = Prefs(this)
    currentTheme = prefs.getCurrentTheme()
    setTheme(currentTheme)
  }
  
  fun navigateTo(action: Int) {
    val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    val navController = navHostFragment.navController
    navController.navigate(action, null)
  }
  
  fun displaySnackBar(message: Int) {
    val snackBar =
            Snackbar.make(
                    findViewById(android.R.id.content),
                    message,
                    Snackbar.LENGTH_LONG
                         )
    snackBar.show()
  }
  
  fun hideActionBar() {
    val isHide = supportActionBar?.isShowing
    isHide.let {
      if (isHide == true) supportActionBar?.hide()
    }
  }
  
  override fun onResume() {
    super.onResume()
    val selectedTheme = Prefs(this).getCurrentTheme()
    if (currentTheme != selectedTheme)
      recreate()
  }
  
  fun showThemes() {
    val dialog = SelectThemeDialogFragment.newInstance(currentTheme)
    dialog.onOk = {
      Prefs(this).currentTheme = dialog.selectedNow.rawValue
      setTheme(dialog.selectedNow)
      recreate()
      dialog.dismiss()
    }
    supportFragmentManager.let { dialog.show(it, "editWord") }
  }
  
  private fun setTheme(currentTheme: Theme) {
    when (currentTheme) {
      Theme.LIGHT -> setTheme(R.style.Theme_App_Light)
      Theme.DARK -> setTheme(R.style.Theme_App_Dark)
      Theme.MINT -> setTheme(R.style.Theme_App_Mint)
      Theme.GRAY -> setTheme(R.style.Theme_App_Gray)
    }
  }
  
  fun renderBackArrayAndTitle(title: String) {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    renderTitle(title)
  }
  
  private fun renderTitle(title: String) {
    val isShow = supportActionBar?.isShowing
    isShow.let {
      if (isShow != true) supportActionBar?.show()
    }
    supportActionBar?.title = title
  }
}