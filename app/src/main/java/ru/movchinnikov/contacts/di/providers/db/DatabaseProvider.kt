package ru.movchinnikov.contacts.di.providers.db

import android.content.Context
import ru.movchinnikov.contacts.data.db.ContactsDatabase
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class DatabaseProvider(
    private val applicationContext: Context
) : Provider<ContactsDatabase> {
    override fun get(): ContactsDatabase {
        return ContactsDatabase.getInstance(applicationContext)
    }
}