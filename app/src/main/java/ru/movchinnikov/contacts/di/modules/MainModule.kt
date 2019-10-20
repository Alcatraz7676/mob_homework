package ru.movchinnikov.contacts.di.modules

import ru.movchinnikov.contacts.ui.main.add.ContactAddInteractor
import ru.movchinnikov.contacts.ui.main.add.IContactAddInteractor
import ru.movchinnikov.contacts.ui.main.contacts.ContactsInteractor
import ru.movchinnikov.contacts.ui.main.contacts.IContactsInteractor
import ru.movchinnikov.contacts.ui.main.detail.ContactDetailInteractor
import ru.movchinnikov.contacts.ui.main.detail.IContactDetailInteractor
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class MainModule : Module() {

    init {
        bind<IContactsInteractor>().toClass<ContactsInteractor>().singleton()
        bind<IContactAddInteractor>().toClass<ContactAddInteractor>().singleton()
        bind<IContactDetailInteractor>().toClass<ContactDetailInteractor>().singleton()
    }
}