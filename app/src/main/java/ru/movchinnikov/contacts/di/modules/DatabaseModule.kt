package ru.movchinnikov.contacts.di.modules

import ru.movchinnikov.contacts.data.db.ContactDao
import ru.movchinnikov.contacts.data.db.ContactsDatabase
import ru.movchinnikov.contacts.data.db.repository.ContactRepository
import ru.movchinnikov.contacts.data.db.repository.IContactRepository
import ru.movchinnikov.contacts.di.providers.db.ContactDaoProvider
import ru.movchinnikov.contacts.di.providers.db.DatabaseProvider
import toothpick.config.Module
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.toClass

class DatabaseModule : Module() {

    init {
        bind<ContactsDatabase>().toProvider(DatabaseProvider::class.java).providesSingleton()
        bind<ContactDao>().toProvider(ContactDaoProvider::class.java).providesSingleton()
        bind<IContactRepository>().toClass<ContactRepository>().singleton()
    }

}