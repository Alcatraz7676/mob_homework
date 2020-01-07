package ru.movchinnikov.contacts.util

import ru.movchinnikov.contacts.data.db.model.Contact
import ru.movchinnikov.contacts.ui.main.contacts.adapters.ContactAdapterModel

fun Contact.toAdapterModel() = ContactAdapterModel(
    id = this.id,
    fullName = "${this.name} ${this.surname} ${this.patronymic}"
)