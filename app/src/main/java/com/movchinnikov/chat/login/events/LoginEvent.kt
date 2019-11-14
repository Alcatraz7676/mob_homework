package com.movchinnikov.chat.login.events

class LoginEvent {

    var eventType: Int = 0
    var errorMesage: String? = null

    companion object {
        const val onSignInError = 0
        const val onSignUpError = 1
        const val onSignInSuccess = 2
        const val onSignUpSuccess = 3
        const val onFailedToRecoverSession = 4
    }
}
