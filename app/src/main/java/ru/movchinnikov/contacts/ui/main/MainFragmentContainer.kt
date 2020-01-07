package ru.movchinnikov.contacts.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import ru.movchinnikov.contacts.R
import ru.movchinnikov.contacts.di.DI
import ru.movchinnikov.contacts.di.modules.MainModule
import ru.movchinnikov.contacts.ui.common.BackButtonListener
import ru.movchinnikov.contacts.ui.common.RouterProvider
import ru.movchinnikov.contacts.ui.common.Screens
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Toothpick
import javax.inject.Inject

class MainFragmentContainer : MvpAppCompatFragment(), RouterProvider, BackButtonListener {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY, DI.MAIN)
        scope.installModules(
            MainModule()
        )
        Toothpick.inject(this, scope)
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @Inject
    lateinit var mRouter: Router

    private val navigator by lazy {
        object : SupportAppNavigator(
            activity, childFragmentManager, R.id.fragment_content
        ) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.fragment_content) == null) {
            mRouter.replaceScreen(Screens.ContactsScreen())
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(DI.MAIN)
    }

    override fun getRouter() = mRouter

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.fragment_content)
        return (fragment != null
            && fragment is BackButtonListener
            && (fragment as BackButtonListener).onBackPressed())
    }
}