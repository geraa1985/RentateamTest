package com.geraa1985.rentateamtest.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IUserView: MvpView {
    fun showAvatar(url: String)
    fun showFirstName(firstName: String)
    fun showLastName(lastName: String)
    fun showEmail(email: String)
    fun showError(message: String)
    fun setUser()
}