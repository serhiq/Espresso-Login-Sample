package com.gmail.uia059466.automatoruisample.signin

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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

class SignInFragment : Fragment(), View.OnFocusChangeListener {
  
  private lateinit var viewModel    : SignInViewModel
  
  private lateinit var loading      : ProgressBar
  private lateinit var email        : EditText
  private lateinit var password     : EditText
  private lateinit var emailInput   : TextInputLayout
  private lateinit var passwordInput: TextInputLayout
  private lateinit var singInButton : Button
  
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    
    val view = inflater.inflate(
            R.layout.sign_in_fragment,
            container,
            false
                               )
    singInButton = view.findViewById(R.id.button_enter)
    singInButton.isEnabled = true
    loading = view.findViewById(R.id.top_progress_bar)
    email = view.findViewById(R.id.email)
    password = view.findViewById(R.id.password)
    emailInput = view.findViewById(R.id.email_layout)
    passwordInput = view.findViewById(R.id.password_layout)
    
    view.findViewById<TextView>(R.id.password_reset_tv)
            .setOnClickListener {
              (activity as MainActivity).navigateTo(R.id.action_signInFragment_to_resetPasswordFragment)
            }
    
    view.findViewById<TextView>(R.id.create_account_tv)
            .setOnClickListener {
              (activity as MainActivity).navigateTo(R.id.action_signInFragment_to_signUpFragment)
            }
    
    setupAppBar()
    setupViewModel()
    setupObservers()
    setupOnBackPressed()
    setupViews()
    
    setHasOptionsMenu(true)
    return view
  }
  
  private fun setupViews() {
    email.onFocusChangeListener = this
    password.onFocusChangeListener = this
    
    email.setOnEditorActionListener { _, actionId, _ ->
      when (actionId) {
        EditorInfo.IME_ACTION_DONE ->
          viewModel.emailChanged(email.text.toString())
      }
      false
    }
    
    password.setOnEditorActionListener { _, actionId, _ ->
      when (actionId) {
        EditorInfo.IME_ACTION_DONE ->
          viewModel.validateAndSignInUser(
                  email.text.toString(),
                  password.text.toString()
                                         )
      }
      false
    }
    
    singInButton.setOnClickListener {
      viewModel.validateAndSignInUser(email.text.toString(), password.text.toString())
    }
  }
  
  private fun setupAppBar() {
    val mainActivity = activity as MainActivity
    mainActivity.renderBackArrayAndTitle(getString(R.string.sign_in_title_app_bar))
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
      renderState(state.emailState,    emailInput)
      renderState(state.passwordState, passwordInput)
    })
    
    viewModel.snackbarText.observe(viewLifecycleOwner, Observer {
      val errorMessage = it ?: return@Observer
      (activity as MainActivity).displaySnackBar(errorMessage)
    })
    
    
    viewModel.navigateToAccount.observe(viewLifecycleOwner, Observer {
      if (it) {
        (activity as MainActivity).navigateTo(R.id.action_signInFragment_to_accountFragment)
      }
    })
    
    viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
      when (isLoading) {
        true  -> loading.visibility = View.VISIBLE
        false -> loading.visibility = View.INVISIBLE
      }
    })
  }
  
  private fun renderState(state: FieldState, textInputLayout: TextInputLayout) {
    val stringError = when (state) {
      is FieldState.Valid -> ""
      is FieldState.Invalid -> getString(state.resError)
      FieldState.Empty -> null
    }
    if (stringError != null) {
      textInputLayout.error = stringError
    }
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = ViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[SignInViewModel::class.java]
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
  
  private fun goBack() {
    findNavController().navigateUp()
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
  
  override fun onFocusChange(v: View?, hasFocus: Boolean) {
    if (hasFocus) return
    val id = v?.id
    if (id == R.id.email) {
      viewModel.emailChanged(email.text.toString())
    } else if (id == R.id.password) {
      viewModel.passwordChanged(password.text.toString())
    }
  }
}