package com.geraa1985.rentateamtest.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUsersListView: MvpView {
    fun initRvUsers()
    fun updateUsersList()
    fun showError(message: String)
}