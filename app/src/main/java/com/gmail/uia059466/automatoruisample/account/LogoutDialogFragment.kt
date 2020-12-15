package com.gmail.uia059466.automatoruisample.account

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.gmail.uia059466.automatoruisample.R

class LogoutDialogFragment : AppCompatDialogFragment() {
  
  var onOk: (() -> Unit)? = null
  
  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    
    val builder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_logout_title))
            .setMessage(getString(R.string.dialog_logout_message))
            .setPositiveButton(getString(R.string.dialog_logout_button)) { _, _ ->
              onOk?.invoke()
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
            }
    return builder.create()
  }
}