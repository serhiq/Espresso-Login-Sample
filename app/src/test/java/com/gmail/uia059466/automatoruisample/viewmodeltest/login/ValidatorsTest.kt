package com.gmail.uia059466.automatoruisample.viewmodeltest.login

import com.gmail.uia059466.automatoruisample.data.FieldState
import com.gmail.uia059466.automatoruisample.util.checkPassword
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidatorsTest {
  
  @Test
  fun shouldHaveMoreThenFourSymbols() {
    assertTrue(checkPassword("1") is FieldState.Invalid)
    assertTrue(checkPassword("12") is FieldState.Invalid)
    assertTrue(checkPassword("133456") is FieldState.Valid)
    assertTrue(checkPassword("133456") is FieldState.Valid)
    assertTrue(checkPassword(" ") is FieldState.Invalid)
  }
}