package com.movchinnikov.chat.login

import com.movchinnikov.chat.lib.GreenRobotEventBus
import com.movchinnikov.chat.login.events.LoginEvent
import com.movchinnikov.chat.login.ui.LoginView
import org.greenrobot.eventbus.Subscribe

class LoginPresenterImpl(private var loginView: LoginView?) : LoginPresenter {
    private val loginInteractor = LoginInteractorImpl()

    override fun validateLogin(email: String, password: String) {
        if (loginView != null) {
            loginView!!.disableInputs()
            loginView!!.showProgress()
        }
        loginInteractor.doSignIn(email, password)
    }

    override fun registerNewUser(email: String, password: String) {
        if (loginView != null) {
            loginView!!.disableInputs()
            loginView!!.showProgress()
        }
        loginInteractor.doSignUp(email, password)
    }

    override fun checkForAuthenticatedUser() {
        if (loginView != null) {
            loginView!!.disableInputs()
            loginView!!.showProgress()
        }

        loginInteractor.checkAlreadyAuthenticated()
    }

    override fun onCreate() {
        GreenRobotEventBus.register(this)
    }

    override fun onDestroy() {
        loginView = null
        GreenRobotEventBus.unregister(this)
    }

    @Subscribe
    override fun onEventMainThread(event: LoginEvent) {
        when (event.eventType) {
            LoginEvent.onSignInError -> onSignInError(event.errorMesage as String)
            LoginEvent.onSignInSuccess -> onSignInSuccess()
            LoginEvent.onSignUpError -> onSignUpError(event.errorMesage as String)
            LoginEvent.onSignUpSuccess -> onSignUpSuccess()
            LoginEvent.onFailedToRecoverSession -> onFailedToRecoverSession()
        }
    }

    private fun onSignInSuccess() {
        if (loginView != null) {
            loginView!!.navigateToMainScreen()
        }
    }

    private fun onSignUpSuccess() {
        if (loginView != null) {
            loginView!!.newUserSuccess()
        }
    }

    private fun onSignInError(error: String) {
        if (loginView != null) {
            loginView!!.hideProgress()
            loginView!!.enableInputs()
            loginView!!.loginError(error)
        }
    }

    private fun onSignUpError(error: String) {
        if (loginView != null) {
            loginView!!.hideProgress()
            loginView!!.enableInputs()
            loginView!!.newUserError(error)
        }
    }

    private fun onFailedToRecoverSession() {
        if (loginView != null) {
            loginView!!.hideProgress()
            loginView!!.enableInputs()
        }
    }
}
