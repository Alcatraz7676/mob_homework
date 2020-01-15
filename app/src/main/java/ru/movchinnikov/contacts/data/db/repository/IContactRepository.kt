package ru.movchinnikov.contacts.data.db.repository

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import ru.movchinnikov.contacts.data.db.model.Contact

interface IContactRepository {

    fun insertContact(contact: Contact): Completable

    fun updateContact(contact: Contact): Completable

    fun deleteContact(id: Long): Maybe<Int>

    fun getContactById(id: Long): Single<Contact>

    fun observeContacts(): Observable<List<Contact>>
}