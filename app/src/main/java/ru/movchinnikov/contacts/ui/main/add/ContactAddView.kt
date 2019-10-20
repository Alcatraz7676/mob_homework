package ru.movchinnikov.contacts.ui.main.add

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ContactAddView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)
}