package com.gmail.uia059466.automatoruisample.data

import android.content.Context
import android.content.SharedPreferences
import com.gmail.uia059466.automatoruisample.themes.Theme

class Prefs(context: Context) {
  
  private val PREFS_FILENAME = "example"
  private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
 
  private val PREF_THEME = "themes"
  
  var currentTheme: String
    get() = prefs.getString(PREF_THEME, Theme.LIGHT.rawValue).toString()
    set(value) = prefs.edit().putString(PREF_THEME, value).apply()
  
  fun getCurrentTheme(): Theme {
    return Theme.fromString(currentTheme)
  }
}
