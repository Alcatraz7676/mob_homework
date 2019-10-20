package ru.movchinnikov.contacts.ui.main.add

import io.reactivex.Completable
import ru.movchinnikov.contacts.data.db.model.Contact

interface IContactAddInteractor {

    fun addContact(contact: Contact): Completable

}