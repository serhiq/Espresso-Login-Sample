package com.gmail.uia059466.automatoruisample.screenshots

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun hasTextInputLayoutHintText(expectedErrorText: String): Matcher<View> = object : TypeSafeMatcher<View>() {
  
  override fun describeTo(description: Description?) { }
  
  override fun matchesSafely(item: View?): Boolean {
    if (item !is TextInputLayout) return false
    val error = item.error ?: return false
    val hint = error.toString()
    return expectedErrorText == hint
  }
}
  
  fun getTextFromTextView(matcher: Matcher<View>): String {
    val stringHolder = arrayOf<String?>(null)
    Espresso.onView(matcher).perform(object : ViewAction {
      override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isAssignableFrom(TextView::class.java)
      }
  
      override fun getDescription(): String {
        return "getting text from a TextView"
      }
  
      override fun perform(uiController: UiController, view: View) {
        val tv = view as TextView //Save, because of check in getConstraints()
        stringHolder[0] = tv.text.toString()
      }
    })
    return stringHolder[0]?:""
  }