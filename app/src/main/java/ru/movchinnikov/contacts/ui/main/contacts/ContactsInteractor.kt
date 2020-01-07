package ru.movchinnikov.contacts.ui.main.contacts

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Maybes
import io.reactivex.schedulers.Schedulers
import ru.movchinnikov.contacts.data.content_provider.IContentProviderRepository
import ru.movchinnikov.contacts.data.db.model.Contact
import ru.movchinnikov.contacts.data.db.repository.IContactRepository
import ru.movchinnikov.contacts.ui.main.contacts.adapters.ContactAdapterModel
import ru.movchinnikov.contacts.util.toAdapterModel
import toothpick.InjectConstructor

@InjectConstructor
class ContactsInteractor(
    private val contactRepository: IContactRepository,
    private val contentProviderRepository: IContentProviderRepository
) : IContactsInteractor {

    override fun getPhoneContacts(): Maybe<List<String>> {
        return contentProviderRepository.getContactNames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getDbContacts(): Single<List<Contact>> {
        return contactRepository.getContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}