package com.gmail.uia059466.automatoruisample.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gmail.uia059466.automatoruisample.MainActivity
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.ViewModelFactory
import com.gmail.uia059466.automatoruisample.data.FieldState
import com.google.android.material.textfield.TextInputLayout

class SignUpFragment : Fragment(), View.OnFocusChangeListener {
  
  private lateinit var viewModel:     SignUpViewModel
  
  private lateinit var loading:       ProgressBar
  private lateinit var email:         EditText
  private lateinit var password:      EditText
  private lateinit var emailInput:    TextInputLayout
  private lateinit var passwordInput: TextInputLayout
  private lateinit var createAccount: Button
  
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    
    val view = inflater.inflate(
            R.layout.sign_up_fragment,
            container,
            false
                               )
    
    val footerTextView: TextView = view.findViewById(R.id.privacy_tv)
    customTextView(footerTextView)
  
    createAccount = view.findViewById(R.id.create_btn)
    createAccount.setOnClickListener {
      loading.visibility = View.VISIBLE
      viewModel.validateAndSignUpUser(email.text.toString(), password.text.toString())
    }
    
    loading = view.findViewById(R.id.top_progress_bar)
   
    email = view.findViewById(R.id.email)
    email.onFocusChangeListener = this
    
    password = view.findViewById(R.id.password)
    password.onFocusChangeListener = this
    
    emailInput = view.findViewById(R.id.email_layout)
    passwordInput = view.findViewById(R.id.password_layout)
  
    setupAppBar()
    setupOnBackPressed()
    setupViewModel()
    setupObservers()
    
    setHasOptionsMenu(true)
    return view
  }
  
  private fun setupAppBar() {
    val mainActivity = activity as MainActivity
    val title = getString(R.string.sign_up_title_app_bar)
    mainActivity.renderBackArrayAndTitle(title)
  }
  
  private fun setupObservers() {
    
    viewModel.state.observe(viewLifecycleOwner, Observer {
      val state = it ?: return@Observer
      renderState(state.emailState, emailInput)
      renderState(state.passwordState, passwordInput)
    })
    
    viewModel.navigateToAccount.observe(viewLifecycleOwner, Observer {
      if (it) {
        (activity as MainActivity).navigateTo(R.id.action_signUpFragment_to_accountFragment)
      }
    })
    
    viewModel.snackbarText.observe(viewLifecycleOwner, Observer {
      (activity as MainActivity).displaySnackBar(it)
    })
    
    viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
      when (isLoading) {
        true -> loading.visibility = View.VISIBLE
        false -> loading.visibility = View.INVISIBLE
      }
    })
  }
  
  private fun renderState(state: FieldState, textInputLayout: TextInputLayout) {
    val stringError = when (state) {
      is FieldState.Valid    -> ""
      is FieldState.Invalid  -> getString(state.resError)
      FieldState.Empty       -> null
    }
    if (stringError != null) {
      textInputLayout.error = stringError
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
  
  private fun customTextView(view: TextView) {
    val spanTxt = SpannableStringBuilder(
            "I agree to the "
                                        )
    spanTxt.append("Term of services")
    spanTxt.setSpan(object : ClickableSpan() {
      override fun onClick(widget: View) {
        runUrl("https://github.com/")
      }
    }, spanTxt.length - "Term of services".length, spanTxt.length, 0)
    spanTxt.append(" and")
    spanTxt.append(" Privacy Policy")
    spanTxt.setSpan(object : ClickableSpan() {
      override fun onClick(widget: View) {
        runUrl("https://github.com/")
      }
    }, spanTxt.length - " Privacy Policy".length, spanTxt.length, 0)
    view.movementMethod = LinkMovementMethod.getInstance()
    view.setText(spanTxt, TextView.BufferType.SPANNABLE)
  }
  
  
  private fun runUrl(uriString: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uriString)))
  }
  
  override fun onFocusChange(
          view: View,
          hasFocus: Boolean
                            ) {
    if (hasFocus) return  // Only consider fields losing focus
    val id = view.id
    if (id == R.id.email) {
      viewModel.emailChanged(email.text.toString())
    } else if (id == R.id.password) {
      viewModel.passwordChanged(password.text.toString())
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
  
  fun goBack() {
    findNavController().navigateUp()
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = ViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]
  }
}