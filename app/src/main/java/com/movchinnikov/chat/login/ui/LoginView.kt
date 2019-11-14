package com.movchinnikov.chat.login.ui

interface LoginView {
    fun enableInputs()
    fun disableInputs()
    fun showProgress()
    fun hideProgress()

    fun handleSignUp()
    fun handleSignIn()

    fun navigateToMainScreen()
    fun loginError(error: String)

    fun newUserSuccess()
    fun newUserError(error: String)
}
