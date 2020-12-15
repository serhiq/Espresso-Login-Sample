package com.gmail.uia059466.automatoruisample.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gmail.uia059466.automatoruisample.MainActivity
import com.gmail.uia059466.automatoruisample.R

class WelcomeFragment : Fragment() {

  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    
    val view = inflater.inflate(
            R.layout.welcome_fragment,
            container,
            false
                               )
    
    val themeBtn = view.findViewById<LinearLayout>(R.id.theme_select_ll)
    themeBtn.setOnClickListener {
      (activity as MainActivity).showThemes()
    }
   
    val enterBtn = view.findViewById<LinearLayout>(R.id.sign_in_email_ll)
    val registerBtn = view.findViewById<Button>(R.id.sign_up_btn)

    enterBtn.setOnClickListener {
      (activity as MainActivity).navigateTo(R.id.action_welcomeFragment_to_signInFragment)
    }
    
    registerBtn.setOnClickListener {
      (activity as MainActivity).navigateTo(R.id.action_welcomeFragment_to_signUpFragment)
    }
    
    setupAppBar()
    setupOnBackPressed()
    return view
  }

  private fun setupAppBar() {
    val mainActivity = activity as MainActivity
    mainActivity.hideActionBar()
  }
  
  private fun setupOnBackPressed() {
    requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner,
                         object : OnBackPressedCallback(true) {
                           override fun handleOnBackPressed() {
                             goBack()
                           }
                         }
                        )
  }
  
  private fun goBack() {
    findNavController().navigateUp()
  }
}