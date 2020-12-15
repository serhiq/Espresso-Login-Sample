package com.gmail.uia059466.automatoruisample.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gmail.uia059466.automatoruisample.MainActivity
import com.gmail.uia059466.automatoruisample.R
import com.gmail.uia059466.automatoruisample.ViewModelFactory

class AccountFragment : Fragment() {
  
  private lateinit var viewModel: AccountViewModel
  private lateinit var emailTv: TextView
  
  override fun onCreateView(
          inflater: LayoutInflater, container: ViewGroup?,
          savedInstanceState: Bundle?
                           ): View? {
    
    val view = inflater.inflate(
            R.layout.signed_in_fragment,
            container,
            false
                               )
    emailTv = view.findViewById(R.id.description_tv)
    
    setupViewModel()
    setupObservers()
    val singOut: Button = view.findViewById(R.id.sign_out_btn)
    singOut.setOnClickListener {
      displayDialogSignOut()
    }
    
    val delete: Button = view.findViewById(R.id.delete_account_btn)
    delete.setOnClickListener {
      displayDialogDeleteAccount()
    }
    
    setupAppBar()
    setupOnBackPressed()
    setHasOptionsMenu(true)
    return view
  }
  
  private fun displayDialogSignOut() {
    val dialog = LogoutDialogFragment()
    dialog.onOk = {
      viewModel.signOut()
    }
    requireActivity().supportFragmentManager.let { dialog.show(it, "signOutDialog") }
  }
  
  private fun setupObservers() {
    viewModel.currentUser.observe(viewLifecycleOwner, Observer {
      val email = it ?: return@Observer
      emailTv.text = email
    })
    
    viewModel.snackbarText.observe(viewLifecycleOwner, Observer {
      val result = it ?: return@Observer
      (activity as MainActivity).displaySnackBar(result)
    })
    
    viewModel.navigateToWelcome.observe(viewLifecycleOwner, Observer {
      if (it) {
        (activity as MainActivity).navigateTo(R.id.action_accountFragment_to_welcomeFragment)
      }
    })
  }
  
  private fun displayDialogDeleteAccount() {
    val dialog = DeleteUserAccountDialogFragment()
    dialog.onOk = {
      displayEnterPasswordDialog()
    }
    requireActivity().supportFragmentManager.let { dialog.show(it, "delete_user") }
  }
  
  private fun displayEnterPasswordDialog() {
    val dialogPassword = EnterPasswordDialogFragment()
    dialogPassword.onOk = {
      if (dialogPassword.text.isNotEmpty()) {
        viewModel.deleteUser(emailTv.text.toString(), dialogPassword.text)
      }
    }
    requireActivity().supportFragmentManager.let { dialogPassword.show(it, "update_user") }
  }
  
  private fun setupAppBar() {
    val mainActivity = activity as MainActivity
    mainActivity.hideActionBar()
  }
  
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> {
        goBack()
        return true
      }
    }
    return true
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
  
  fun goBack() {
    findNavController().navigateUp()
  }
  
  private fun setupViewModel() {
    val application = requireNotNull(this.activity).application
    val viewModelFactory = ViewModelFactory(application)
    viewModel = ViewModelProvider(this, viewModelFactory)[AccountViewModel::class.java]
  }
}