package ru.movchinnikov.contacts.data.db.repository

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.movchinnikov.contacts.data.db.ContactDao
import ru.movchinnikov.contacts.data.db.model.Contact
import toothpick.InjectConstructor

@InjectConstructor
class ContactRepository(
    private val contactDao: ContactDao
) : IContactRepository {

    override fun insertContact(contact: Contact): Completable {
        return contactDao.insert(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateContact(contact: Contact): Completable {
        return contactDao.update(contact)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteContact(id: Long): Maybe<Int> {
        return contactDao.deleteContactById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getContactById(id: Long): Single<Contact> {
        return contactDao.getContactById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun observeContacts(): Observable<List<Contact>> {
        return contactDao.observeAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}