package ru.movchinnikov.contacts.ui.activity

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import ru.movchinnikov.contacts.R
import ru.movchinnikov.contacts.di.DI
import ru.movchinnikov.contacts.ui.common.BackButtonListener
import ru.movchinnikov.contacts.ui.main.MainFragmentContainer
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity() {

    init {
        val scope = Toothpick.openScopes(DI.APP, DI.ACTIVITY)
        Toothpick.inject(this, scope)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_content, MainFragmentContainer())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(DI.ACTIVITY)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.activity_content)
        if (fragment != null
            && fragment is BackButtonListener &&
            (fragment as BackButtonListener).onBackPressed()
        ) {
            return
        } else {
            super.onBackPressed()
        }
    }
}