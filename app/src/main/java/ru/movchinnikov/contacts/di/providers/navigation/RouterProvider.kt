package ru.movchinnikov.contacts.di.providers.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import toothpick.InjectConstructor
import javax.inject.Provider

@InjectConstructor
class RouterProvider(
    private val cicerone: Cicerone<Router>
) : Provider<Router> {

    override fun get(): Router {
        return cicerone.router
    }
}