package com.geraa1985.rentateamtest.mvp.presenter

import com.geraa1985.rentateamtest.mvp.view.IMainView
import com.geraa1985.rentateamtest.navigation.FragmentScreen
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter: MvpPresenter<IMainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(FragmentScreen.usersListScreen())
    }

    fun openUsers() {
        router.newRootScreen(FragmentScreen.usersListScreen())
    }

    fun openAbout() {
        router.newRootScreen(FragmentScreen.aboutScreen())
    }

    fun backClicked() {
        router.exit()
    }
}