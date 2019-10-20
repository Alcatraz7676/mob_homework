package ru.movchinnikov.contacts.di.providers.db

import ru.movchinnikov.contacts.data.db.ContactDao
import ru.movchinnikov.contacts.data.db.ContactsDatabase
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class ContactDaoProvider(
    private val database: ContactsDatabase
) : Provider<ContactDao> {
    override fun get(): ContactDao {
        return database.contactsDao()
    }
}