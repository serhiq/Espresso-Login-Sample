package com.gmail.uia059466.automatoruisample.resetpassword

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gmail.uia059466.automatoruisample.MainActivity
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.ViewModelFactory
import com.gmail.uia059466.automatoruisample.data.FieldState
import com.google.android.material.textfield.TextInputLayout

class ResetPasswordFragment : Fragment(), View.OnFocusChangeListener {
  
  private lateinit var viewModel: ResetPasswordViewModel
  
  private lateinit var loading: ProgressBar
  private lateinit var email: EditText
  private lateinit var emailInput: TextInputLayout
  
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    
    val view = inflater.inflate(
            R.layout.reset_password_fragment,
            container,
            false
                               )
    
    val sendButton: Button = view.findViewById(R.id.send_button)
    sendButton.setOnClickListener {
      viewModel.validateAndReset(email.text.toString())
    }
    
    loading = view.findViewById(R.id.top_progress_bar)
   
    email = view.findViewById(R.id.email)
    email.onFocusChangeListener = this
    
    emailInput = view.findViewById(R.id.email_layout)
    
    setupViewModel()
    setupObservers()
    setupAppBar()
    setupOnBackPressed()
    setHasOptionsMenu(true)
    return view
  }
  
  private fun setupAppBar() {
    val mainActivity = activity as MainActivity
    mainActivity.renderBackArrayAndTitle(getString(R.string.reset_password_title_app_bar))
  }
  
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        hideKeyboard(requireActivity())
        goBack()
        return true
      }
    }
    return true
  }
  
  private fun setupObservers() {
    
    viewModel.state.observe(viewLifecycleOwner, Observer {
      val state = it ?: return@Observer
      renderState(state, emailInput)
    })
    
    viewModel.snackbarText.observe(viewLifecycleOwner, Observer {
      val errorMessage = it ?: return@Observer
      (activity as MainActivity).displaySnackBar(errorMessage)
    })
    
    viewModel.showEmailSentDialog.observe(viewLifecycleOwner, Observer {
      showEmailSentDialog(email.text.toString())
    })
  }
  
  private fun renderState(state: FieldState, textInputLayout: TextInputLayout) {
    val stringError = when (state) {
      is FieldState.Valid   -> ""
      is FieldState.Invalid -> getString(state.resError)
      FieldState.Empty      -> null
    }
    if (stringError != null) {
      textInputLayout.error = stringError
    }
  }
  
  private fun showEmailSentDialog(email: String) {
    AlertDialog.Builder(requireContext())
            .setTitle(R.string.title_confirm_recover_password)
            .setMessage(getString(R.string.confirm_recovery_body, email))
            .setOnDismissListener {
              goBack()
            }
            .setPositiveButton(android.R.string.ok, null)
            .show()
  }
  
  override fun onFocusChange(
          view: View,
          hasFocus: Boolean
                            ) {
    if (hasFocus) return  
    val id = view.id
    if (id == R.id.email) {
      viewModel.emailChanged(email.text.toString())
    }
  }
  
  private fun hideKeyboard(activity: Activity) {
    try {
      val inputManager: InputMethodManager = activity
              .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      val currentFocusedView = activity.currentFocus
      if (currentFocusedView != null) {
        inputManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
                                            )
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
  
  private fun setupOnBackPressed() {
    requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner,
                         object : OnBackPressedCallback(true) {
                           override fun handleOnBackPressed() {
                             hideKeyboard(requireActivity())
                             goBack()
                           }
                         }
                        )
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = ViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[ResetPasswordViewModel::class.java]
  }
  
  fun goBack() {
    findNavController().navigateUp()
  }
}