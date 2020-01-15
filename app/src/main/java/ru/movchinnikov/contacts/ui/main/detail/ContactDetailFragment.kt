package ru.movchinnikov.contacts.ui.main.detail

import android.os.Bundle
import android.view.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_detail.*
import ru.movchinnikov.contacts.R
import ru.movchinnikov.contacts.data.db.model.Contact
import ru.movchinnikov.contacts.di.DI
import ru.movchinnikov.contacts.ui.activity.MainActivity
import ru.movchinnikov.contacts.ui.common.BackButtonListener
import ru.movchinnikov.contacts.ui.common.BaseFragment
import ru.movchinnikov.contacts.ui.common.RouterProvider
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class ContactDetailFragment : BaseFragment(), ContactDetailView, BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.MAIN)
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var contactDetailInteractor: IContactDetailInteractor

    @InjectPresenter
    lateinit var presenter: ContactDetailPresenter

    @ProvidePresenter
    fun providePresenter() = ContactDetailPresenter(contactDetailInteractor, router)

    private val router: Router
        get() = (parentFragment as RouterProvider).getRouter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        arguments?.let {
            val contactId = arguments!!.getLong(CONTACT_ID, -1)
            presenter.showContact(contactId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).setSupportActionBar(null)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail_contact, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_contact -> {
                arguments?.let {
                    val contactId = arguments!!.getLong(CONTACT_ID, -1)
                    if (contactId != -1L)
                        presenter.deleteContact(contactId)
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setContact(contact: Contact) {
        with(contact) {
            fullname_textview.text = "$name $surname $patronymic"
            phone_textview.text = phone
            email_textview.text = email
        }
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    private fun setupToolbar() {
        with(toolbar) {
            (activity as MainActivity).setSupportActionBar(this)
            title = resources.getString(R.string.app_name)
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener {
                presenter.onBackPressed()
            }
        }
        setHasOptionsMenu(true)
    }

    companion object {
        private const val CONTACT_ID = "contact_id"

        fun newInstance(contactId: Long): ContactDetailFragment {
            val contactDetailFragment = ContactDetailFragment()
            val bundle = Bundle()
            bundle.putLong(CONTACT_ID, contactId)
            contactDetailFragment.arguments = bundle
            return contactDetailFragment
        }
    }
}