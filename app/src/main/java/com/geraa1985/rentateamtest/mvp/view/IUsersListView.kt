package com.geraa1985.rentateamtest.mvp.view

import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUsersListView: MvpView {
    fun initRvUsers()
    fun updateUsersList(oldList: List<User>, newList: List<User>)
    fun showError(message: String)
    fun showProgress()
    fun hideProgress()
}