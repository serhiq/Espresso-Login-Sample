package com.gmail.uia059466.automatoruisample.util

import com.gmail.uia059466.automatoruisample.data.LoginRepositoryImpl
import com.gmail.uia059466.automatoruisample.data.UserRepository

fun provideUserRepository(): UserRepository {
    return LoginRepositoryImpl.getInstance()
}