package ru.movchinnikov.contacts.ui.main.detail

import io.reactivex.Maybe
import io.reactivex.Single
import ru.movchinnikov.contacts.data.db.model.Contact

interface IContactDetailInteractor {

    fun getContactById(id: Long): Single<Contact>

    fun deleteContact(id: Long): Maybe<Int>

}