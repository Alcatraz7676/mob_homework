package com.movchinnikov.chat.login

import com.movchinnikov.chat.login.events.LoginEvent

interface LoginPresenter {
    fun onCreate()
    fun onDestroy()
    fun checkForAuthenticatedUser()
    fun onEventMainThread(event: LoginEvent)
    fun validateLogin(email: String, password: String)
    fun registerNewUser(email: String, password: String)
}
