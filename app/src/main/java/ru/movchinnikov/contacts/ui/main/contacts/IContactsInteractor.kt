package ru.movchinnikov.contacts.ui.main.contacts

import io.reactivex.Maybe
import io.reactivex.Observable
import ru.movchinnikov.contacts.data.db.model.Contact

interface IContactsInteractor {

    fun getPhoneContacts(): Maybe<List<String>>

    fun observeDbContacts():Observable<List<Contact>>


}