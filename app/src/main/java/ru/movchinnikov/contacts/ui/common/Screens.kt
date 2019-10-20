package ru.movchinnikov.contacts.ui.common

import androidx.fragment.app.Fragment
import ru.movchinnikov.contacts.ui.main.add.ContactAddFragment
import ru.movchinnikov.contacts.ui.main.contacts.ContactsFragment
import ru.movchinnikov.contacts.ui.main.detail.ContactDetailFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ContactsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ContactsFragment()
        }
    }

    class ContactAddScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ContactAddFragment()
        }
    }

    class ContactDetailScreen(private val contactId: Long) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ContactDetailFragment.newInstance(contactId)
        }
    }
}