package ru.movchinnikov.contacts.ui.main.contacts

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
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
        Log.i(LOG_TAG, "onFirstViewAttach()")
        viewState.requestContactPermission()
        getDbContacts()
    }

    fun getPhoneContacts() {
        contactsInteractor.getPhoneContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { contacts ->
                viewState.addPhoneContacts(
                    contacts.map { name ->
                        ContactAdapterModel(fullName = name, isContact = true)
                    }
                )
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

    private fun getDbContacts() {
        contactsInteractor.getDbContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { contacts -> contacts.map(Contact::toAdapterModel) }
            .subscribe { contacts ->
                Log.i(LOG_TAG, contacts.joinToString())
                viewState.addDbContacts(contacts)
            }.addTo(compositeDisposable)
    }

    companion object {
        private const val LOG_TAG = "ContactsPresenter"
    }
}