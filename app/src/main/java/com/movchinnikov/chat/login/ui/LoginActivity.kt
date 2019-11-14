package com.movchinnikov.chat.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.movchinnikov.chat.R
import com.movchinnikov.chat.contactlist.ui.ContactListActivity
import com.movchinnikov.chat.login.LoginPresenter
import com.movchinnikov.chat.login.LoginPresenterImpl
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginPresenter = LoginPresenterImpl(this)
        loginPresenter.onCreate()
        loginPresenter.checkForAuthenticatedUser()

        btnSignup.setOnClickListener { handleSignUp() }
        btnSignin.setOnClickListener { handleSignIn() }
    }

    override fun onDestroy() {
        loginPresenter.onDestroy()
        super.onDestroy()
    }

    override fun handleSignUp() {
        loginPresenter.registerNewUser(
            editTxtEmail.text.toString(),
            editTxtPassword.text.toString()
        )
    }

    override fun handleSignIn() {
        loginPresenter.validateLogin(
            editTxtEmail.text.toString(),
            editTxtPassword.text.toString()
        )
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun disableInputs() {
        setInputs(false)
    }

    override fun enableInputs() {
        setInputs(true)
    }

    override fun loginError(error: String) {
        editTxtPassword.setText("")
        val msgError = String.format(getString(R.string.login_error_message_signin), error)
        editTxtPassword.error = msgError
    }

    override fun navigateToMainScreen() {
        startActivity(Intent(this, ContactListActivity::class.java))
    }

    override fun newUserError(error: String) {
        editTxtPassword.setText("")
        val msgError = String.format(getString(R.string.login_error_message_signup), error)
        editTxtPassword.error = msgError
    }

    override fun newUserSuccess() {
        Snackbar.make(
            layoutMainContainer,
            R.string.login_notice_message_useradded,
            Snackbar.LENGTH_SHORT
        )
            .show()
    }

    private fun setInputs(enabled: Boolean) {
        btnSignin.isEnabled = enabled
        btnSignup.isEnabled = enabled
        editTxtEmail.isEnabled = enabled
        editTxtPassword.isEnabled = enabled
    }
}
