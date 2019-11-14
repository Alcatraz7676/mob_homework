package com.movchinnikov.chat.login

class LoginInteractorImpl : LoginInteractor {
    private val loginRepository: LoginRepository = LoginRepositoryImpl()

    override fun doSignUp(email: String, password: String) {
        loginRepository.signUp(email, password)
    }

    override fun doSignIn(email: String, password: String) {
        loginRepository.signIn(email, password)
    }

    override fun checkAlreadyAuthenticated() {
        loginRepository.checkAlreadyAuthenticated()
    }
}
