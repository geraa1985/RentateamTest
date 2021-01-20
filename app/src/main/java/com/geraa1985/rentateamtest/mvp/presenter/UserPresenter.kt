package com.geraa1985.rentateamtest.mvp.presenter

import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import com.geraa1985.rentateamtest.mvp.view.IUserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UserPresenter: MvpPresenter<IUserView>() {

    @Inject
    lateinit var router: Router

    private lateinit var user: User

    init {
        viewState.setUser()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        showAvatar()
        showFirstName()
        showLastName()
        showEmail()
    }

    fun setUser(user: User?) {
        user?.let { this.user = it }
    }

    private fun showAvatar() {
        user.avatar?.let {
            viewState.showAvatar(it)
        }
    }

    private fun showFirstName() {
        user.firstName?.let {
            viewState.showFirstName(it)
        }
    }

    private fun showLastName() {
        user.lastName?.let {
            viewState.showLastName(it)
        }
    }

    private fun showEmail() {
        user.email?.let {
            viewState.showEmail(it)
        }
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}