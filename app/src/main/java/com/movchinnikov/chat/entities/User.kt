package com.movchinnikov.chat.entities

data class User(
    var email: String? = null,
    var isOnline: Boolean = false,
    var contacts: Map<String, Boolean>? = null
) {
    companion object {
        const val ONLINE = true
        const val OFFLINE = false
    }
}