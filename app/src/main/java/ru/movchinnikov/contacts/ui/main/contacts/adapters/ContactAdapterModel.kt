package ru.movchinnikov.contacts.ui.main.contacts.adapters

data class ContactAdapterModel(
    val id: Long = -1L,
    val fullName: String,
    val isContact: Boolean = false
)