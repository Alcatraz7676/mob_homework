package ru.movchinnikov.contacts.ui.main.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_add.*
import ru.movchinnikov.contacts.R
import ru.movchinnikov.contacts.di.DI
import ru.movchinnikov.contacts.ui.activity.MainActivity
import ru.movchinnikov.contacts.ui.common.BackButtonListener
import ru.movchinnikov.contacts.ui.common.BaseFragment
import ru.movchinnikov.contacts.ui.common.RouterProvider
import ru.terrakok.cicerone.Router
import toothpick.Toothpick
import javax.inject.Inject

class ContactAddFragment : BaseFragment(), ContactAddView, BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.MAIN)
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var contactAddInteractor: IContactAddInteractor

    @InjectPresenter
    lateinit var presenter: ContactAddPresenter

    @ProvidePresenter
    fun providePresenter() = ContactAddPresenter(contactAddInteractor, router)

    private val router: Router
        get() = (parentFragment as RouterProvider).getRouter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        add_button.clicks().subscribe {
            presenter.addContact(
                addContactName_edittext.text.toString(),
                addContactSurname_edittext.text.toString(),
                addContactPatronymic_edittext.text.toString(),
                addContactPhone_edittext.text.toString(),
                addContactEmail_edittext.text.toString()
            )
        }.addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    override fun showToast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
    }

    private fun setupToolbar() {
        with(toolbar) {
            toolbar.title = "Добавить контакт"
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener {
                presenter.onBackPressed()
            }
        }
    }
}