package ru.movchinnikov.contacts.ui.main.contacts

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.movchinnikov.contacts.ui.main.contacts.adapters.ContactAdapterModel

interface ContactsView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addDbContacts(contacts: List<ContactAdapterModel>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addPhoneContacts(contacts: List<ContactAdapterModel>)

    @StateStrategyType(SkipStrategy::class)
    fun requestContactPermission()
}