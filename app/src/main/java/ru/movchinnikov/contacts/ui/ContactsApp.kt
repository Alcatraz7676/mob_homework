package ru.movchinnikov.contacts.ui

import android.app.Application
import ru.movchinnikov.contacts.di.DI
import ru.movchinnikov.contacts.di.modules.AppModule
import ru.movchinnikov.contacts.di.modules.ContentProviderModule
import ru.movchinnikov.contacts.di.modules.DatabaseModule
import ru.movchinnikov.contacts.di.modules.NavigationModule
import toothpick.Toothpick

class ContactsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        val scope = Toothpick.openScope(DI.APP)
        scope.installModules(
            AppModule(this),
            DatabaseModule(),
            NavigationModule(),
            ContentProviderModule()
        )
        Toothpick.inject(this, scope)
    }
}