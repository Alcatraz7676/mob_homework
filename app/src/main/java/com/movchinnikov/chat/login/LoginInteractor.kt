package com.movchinnikov.chat.login

interface LoginInteractor {
    fun checkAlreadyAuthenticated()
    fun doSignUp(email: String, password: String)
    fun doSignIn(email: String, password: String)
}
