package ru.movchinnikov.contacts.ui.main.contacts

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_contacts.*
import ru.movchinnikov.contacts.R
import ru.movchinnikov.contacts.di.DI
import ru.movchinnikov.contacts.ui.common.BackButtonListener
import ru.movchinnikov.contacts.ui.common.BaseFragment
import ru.movchinnikov.contacts.ui.common.RouterProvider
import ru.movchinnikov.contacts.ui.main.contacts.adapters.ContactAdapterModel
import ru.movchinnikov.contacts.ui.main.contacts.adapters.ContactsAdapter
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class ContactsFragment : BaseFragment(), ContactsView, BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.MAIN)
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var contactsInteractor: IContactsInteractor

    @InjectPresenter
    lateinit var presenter: ContactsPresenter

    @ProvidePresenter
    fun providePresenter() = ContactsPresenter(contactsInteractor, router)

    private val router: Router
        get() = (parentFragment as RouterProvider).getRouter()

    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        initAdapter()
        fab.clicks().subscribe {
            presenter.navigateToContactAdd()
        }.addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recycler_view.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun addDbContacts(contacts: List<ContactAdapterModel>) {
        contactsAdapter.clearDbItems()
        addContacts(contacts)
    }

    override fun addPhoneContacts(contacts: List<ContactAdapterModel>) {
        addContacts(contacts)
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    override fun requestContactPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.READ_CONTACTS),
            MY_PERMISSIONS_REQUEST_READ_CONTACTS
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS)
            presenter.getPhoneContacts()
        else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun addContacts(contacts: List<ContactAdapterModel>) {
        contactsAdapter.addItems(contacts)
    }

    private fun setupToolbar() {
        toolbar.title = resources.getString(R.string.app_name)
    }

    private fun initAdapter() {
        contactsAdapter = ContactsAdapter { contactId ->
            presenter.navigateToContactDetail(contactId)
        }
        with(recycler_view) {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(
                    activity,
                    DividerItemDecoration.VERTICAL
                )
            )
            setHasFixedSize(true)
        }
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 228
    }
}