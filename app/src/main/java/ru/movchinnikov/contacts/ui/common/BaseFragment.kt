package ru.movchinnikov.contacts.ui.common

import com.arellomobile.mvp.MvpAppCompatFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : MvpAppCompatFragment() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

}