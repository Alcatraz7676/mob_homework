package ru.movchinnikov.contacts.ui.main.detail

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import ru.movchinnikov.contacts.ui.common.BasePresenter
import ru.movchinnikov.contacts.ui.main.add.IContactAddInteractor
import ru.movchinnikov.contacts.ui.main.contacts.ContactsPresenter
import ru.terrakok.cicerone.Router

@InjectViewState
class ContactDetailPresenter(
    private val contactDetailInteractor: IContactDetailInteractor,
    private val router: Router
) : BasePresenter<ContactDetailView>() {

    fun showContact(contactId: Long) {
        contactDetailInteractor.getContactById(contactId)
            .subscribe({
                viewState.setContact(it)
            }, {
                Log.e(LOG_TAG, it.localizedMessage!!)
            }).addTo(compositeDisposable)
    }

    fun deleteContact(contactId: Long) {
        contactDetailInteractor.deleteContact(contactId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    router.exit()
                }, {
                    Log.e(LOG_TAG, it.localizedMessage)
                }
            ).addTo(compositeDisposable)
    }

    fun onBackPressed() {
        router.exit()
    }

    companion object {
        private const val LOG_TAG = "ContactDetailPresenter"
    }

}