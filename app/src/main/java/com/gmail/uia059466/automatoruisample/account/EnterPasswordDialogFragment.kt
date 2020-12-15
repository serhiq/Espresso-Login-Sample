package com.gmail.uia059466.automatoruisample.account

import android.app.Dialog
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.gmail.uia059466.automatoruisample.R
import com.google.android.material.textfield.TextInputEditText

class EnterPasswordDialogFragment : AppCompatDialogFragment() {
  
  var onOk: (() -> Unit)? = null
  var text = ""
  
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val view = requireActivity().layoutInflater.inflate(R.layout.dialog_enter_password, null)
    val editText = view.findViewById<TextInputEditText>(R.id.edit_text)
    val builder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_enter_password_title))
            .setView(view)
            .setPositiveButton(android.R.string.ok) { _, _ ->
              text = editText.text.toString()
              onOk?.invoke()
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
              dismiss()
            }
    
    val dialog = builder.create()
    dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    return dialog
  }
}