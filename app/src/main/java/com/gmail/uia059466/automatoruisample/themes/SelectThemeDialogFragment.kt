package com.gmail.uia059466.automatoruisample.themes

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.gmail.uia059466.automatoruisample.R

class SelectThemeDialogFragment : AppCompatDialogFragment() {
  
  var onOk: (() -> Unit)? = null
  lateinit var selectedNow: Theme
  
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val current = arguments?.getString(CURRENT_THEME)
    
    val selected = when {
      current != null -> Theme.fromString(current)
      else -> Theme.LIGHT
    }
    
    val themes = Theme.values()
    val titleThemes = themes.map { getTitleForTheme(it) }
    
    return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_select_themes_title))
            .setSingleChoiceItems(
                    titleThemes.toTypedArray(),
                    selected.ordinal
                                 ) { dialog, position ->
              selectedNow = themes[position]
              onOk?.invoke()
              dialog.dismiss()
            }
            .create()
  }
  
  private fun getTitleForTheme(theme: Theme) = when (theme) {
    Theme.LIGHT -> getString(R.string.theme_light)
    Theme.DARK ->  getString(R.string.theme_dark)
    Theme.MINT ->  getString(R.string.theme_mint)
    Theme.GRAY ->  getString(R.string.theme_gray)
  }
  
  companion object {
    
    private const val CURRENT_THEME = "current_theme"
    
    fun newInstance(currentTheme: Theme): SelectThemeDialogFragment {
      val dialog = SelectThemeDialogFragment()
      val args = Bundle().apply {
        putString(CURRENT_THEME, currentTheme.rawValue)
      }
      dialog.arguments = args
      return dialog
    }
  }
}