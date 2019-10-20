package ru.movchinnikov.contacts.ui.main.detail

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.movchinnikov.contacts.data.db.model.Contact

interface ContactDetailView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setContact(contact: Contact)

}