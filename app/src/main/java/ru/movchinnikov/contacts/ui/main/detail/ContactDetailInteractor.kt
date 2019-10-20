package ru.movchinnikov.contacts.ui.main.detail

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.movchinnikov.contacts.data.db.model.Contact
import ru.movchinnikov.contacts.data.db.repository.ContactRepository
import ru.movchinnikov.contacts.data.db.repository.IContactRepository
import toothpick.InjectConstructor

@InjectConstructor
class ContactDetailInteractor(
    private val contactRepository: IContactRepository
) : IContactDetailInteractor {

    override fun getContactById(id: Long): Single<Contact> {
        return contactRepository.getContactById(id)
    }

    override fun deleteContact(id: Long): Maybe<Int> {
        return contactRepository.deleteContact(id)
    }
}