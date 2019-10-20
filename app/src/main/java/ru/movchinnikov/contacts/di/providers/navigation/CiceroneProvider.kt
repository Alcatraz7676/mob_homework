package ru.movchinnikov.contacts.di.providers.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class CiceroneProvider : Provider<Cicerone<Router>> {

    override fun get(): Cicerone<Router> {
        return Cicerone.create()
    }
}