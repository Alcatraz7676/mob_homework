package com.movchinnikov.chat.domain

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.movchinnikov.chat.entities.User
import java.util.HashMap

object FirebaseHelper {
    private const val SEPARATOR = "___"
    private const val CHATS_PATH = "chats"
    private const val USERS_PATH = "users"
    private const val CONTACTS_PATH = "contacts"

    val dataReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun getAuthUserEmail(): String? {
        val user = FirebaseAuth.getInstance().currentUser
        return user?.email
    }

    fun getUserReference(email: String?): DatabaseReference? {
        var userReference: DatabaseReference? = null
        if (email != null) {
            val emailKey = email.replace(".", "_")
            userReference = dataReference.root.child(USERS_PATH).child(emailKey)
        }
        return userReference
    }

    fun getMyUserReference() = getUserReference(getAuthUserEmail())

    fun getContactsReference(email: String?) = getUserReference(email)!!.child(CONTACTS_PATH)

    fun getMyContactsReference() = getContactsReference(getAuthUserEmail())

    fun getOneContactReference(mainEmail: String?, childEmail: String): DatabaseReference {
        val childKey = childEmail.replace(".", "_")
        return getUserReference(mainEmail)!!.child(CONTACTS_PATH).child(childKey)
    }

    fun getChatsReference(receiver: String): DatabaseReference {
        val keySender = getAuthUserEmail()!!.replace(".", "_")
        val keyReceiver = receiver.replace(".", "_")

        var keyChat = keySender + SEPARATOR + keyReceiver
        if (keySender > keyReceiver) {
            keyChat = keyReceiver + SEPARATOR + keySender
        }
        return dataReference.root.child(CHATS_PATH).child(keyChat)
    }

    fun changeUserConnectionStatus(online: Boolean) {
        if (getMyUserReference() != null) {
            val updates = HashMap<String, Any>()
            updates["online"] = online
            getMyUserReference()!!.updateChildren(updates)

            notifyContactsOfConnectionChange(online)
        }
    }

    fun notifyContactsOfConnectionChange(online: Boolean, signoff: Boolean) {
        val myEmail = getAuthUserEmail()
        getMyContactsReference().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    val email = child.key
                    val reference = getOneContactReference(email, myEmail!!)
                    reference.setValue(online)
                }
                if (signoff) {
                    FirebaseAuth.getInstance().signOut()
                }
            }

            override fun onCancelled(firebaseError: DatabaseError) {}
        })
    }

    fun notifyContactsOfConnectionChange(online: Boolean) {
        notifyContactsOfConnectionChange(online, false)
    }

    fun signOff() {
        notifyContactsOfConnectionChange(User.OFFLINE, true)
    }
}