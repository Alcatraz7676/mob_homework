package com.movchinnikov.chat.login

interface LoginRepository {
    fun signUp(email: String, password: String)
    fun signIn(email: String, password: String)
    fun checkAlreadyAuthenticated()
}
