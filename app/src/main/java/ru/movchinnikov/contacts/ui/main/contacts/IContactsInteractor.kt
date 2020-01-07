package ru.movchinnikov.contacts.ui.main.contacts

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import ru.movchinnikov.contacts.data.db.model.Contact

interface IContactsInteractor {

    fun getPhoneContacts(): Maybe<List<String>>

    fun getDbContacts(): Single<List<Contact>>
}