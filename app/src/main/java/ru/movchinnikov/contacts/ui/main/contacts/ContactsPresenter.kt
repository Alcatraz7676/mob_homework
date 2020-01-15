package ru.movchinnikov.contacts.ui.main.contacts

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Maybes
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.movchinnikov.contacts.data.db.model.Contact
import ru.movchinnikov.contacts.ui.common.BasePresenter
import ru.movchinnikov.contacts.ui.common.Screens
import ru.movchinnikov.contacts.ui.main.contacts.adapters.ContactAdapterModel
import ru.movchinnikov.contacts.util.toAdapterModel
import ru.terrakok.cicerone.Router

@InjectViewState
class ContactsPresenter(
    private val contactsInteractor: IContactsInteractor,
    private val router: Router
) : BasePresenter<ContactsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.requestContactPermission()
    }

    fun observeAllContacts() {
        Observables.combineLatest(
            emitPhoneContacts().toObservable(),
            emitDbContacts()
        ) { phoneContacts, dbContacts ->
            phoneContacts.toMutableList().apply { addAll(dbContacts) }
        }.subscribe { contacts ->
            viewState.addContacts(contacts)
        }.addTo(compositeDisposable)
    }

    fun observeDbContacts() {
        emitDbContacts()
            .subscribe { contacts ->
                viewState.addContacts(contacts)
            }.addTo(compositeDisposable)
    }

    fun navigateToContactDetail(contactId: Long) {
        router.navigateTo(Screens.ContactDetailScreen(contactId))
    }

    fun navigateToContactAdd() {
        router.navigateTo(Screens.ContactAddScreen())
    }

    fun onBackPressed() {
        router.exit()
    }

    private fun emitPhoneContacts(): Maybe<List<ContactAdapterModel>> {
        return contactsInteractor.getPhoneContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { rawContacts ->
                rawContacts.map { name ->
                    ContactAdapterModel(fullName = name, isContact = true)
                }
            }
    }

    private fun emitDbContacts(): Observable<List<ContactAdapterModel>> {
        return contactsInteractor.observeDbContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { contacts -> contacts.map(Contact::toAdapterModel) }
    }
}