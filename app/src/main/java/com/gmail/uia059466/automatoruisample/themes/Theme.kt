package com.gmail.uia059466.automatoruisample.themes

enum class Theme(val rawValue: String) {
  LIGHT(rawValue = "clean"),
  DARK (rawValue = "dark" ),
  MINT (rawValue = "mint" ),
  GRAY (rawValue = "gray" );
  
  companion object {
    fun fromString(raw: String): Theme {
      return Theme.values().single { it.rawValue == raw }
    }
    
    fun toString(value: Theme): String = value.rawValue
  }
}