package com.movchinnikov.chat.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.movchinnikov.chat.domain.FirebaseHelper
import com.movchinnikov.chat.entities.User
import com.movchinnikov.chat.lib.GreenRobotEventBus
import com.movchinnikov.chat.login.events.LoginEvent

class LoginRepositoryImpl : LoginRepository {
    private var myUserReference: DatabaseReference? = FirebaseHelper.getMyUserReference()

    override fun signUp(email: String, password: String) {
        val register = FirebaseAuth.getInstance()
        try {
            register.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    postEvent(LoginEvent.onSignUpSuccess)
                    signIn(email, password)
                }
                .addOnFailureListener { e -> postEvent(LoginEvent.onSignUpError, e.message) }
        } catch (e: Exception) {
            postEvent(LoginEvent.onSignUpError, e.message)
        }
    }

    override fun signIn(email: String, password: String) {
        try {
            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    myUserReference = FirebaseHelper.getMyUserReference()
                    myUserReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            initSignIn(snapshot)
                        }

                        override fun onCancelled(firebaseError: DatabaseError) {
                            postEvent(LoginEvent.onSignInError, firebaseError.message)
                        }
                    })
                }
                .addOnFailureListener { e -> postEvent(LoginEvent.onSignInError, e.message) }
        } catch (e: Exception) {
            postEvent(LoginEvent.onSignInError, e.message)
        }
    }

    override fun checkAlreadyAuthenticated() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            myUserReference = FirebaseHelper.getMyUserReference()
            myUserReference!!.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    initSignIn(snapshot)
                }

                override fun onCancelled(firebaseError: DatabaseError) {
                    postEvent(LoginEvent.onSignInError, firebaseError.message)
                }
            })
        } else {
            postEvent(LoginEvent.onFailedToRecoverSession)
        }
    }

    private fun registerNewUser() {
        val email = FirebaseHelper.getAuthUserEmail()
        if (email != null) {
            val currentUser = User(email, true, null)
            myUserReference!!.setValue(currentUser)
        }
    }

    private fun initSignIn(snapshot: DataSnapshot) {
        val currentUser = snapshot.getValue<User>(User::class.java)

        if (currentUser == null) {
            registerNewUser()
        }
        FirebaseHelper.changeUserConnectionStatus(User.ONLINE)
        postEvent(LoginEvent.onSignInSuccess)
    }

    private fun postEvent(type: Int, errorMessage: String? = null) {
        val loginEvent = LoginEvent()
        loginEvent.eventType = type
        if (errorMessage != null) {
            loginEvent.errorMesage = errorMessage
        }

        GreenRobotEventBus.post(loginEvent)
    }
}
