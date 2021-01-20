package com.geraa1985.rentateamtest.mvp.presenter

import com.geraa1985.rentateamtest.mvp.view.IAboutView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AboutPresenter: MvpPresenter<IAboutView>() {

    @Inject
    lateinit var router: Router

    fun backClicked(): Boolean {
        router.exit()
        return true
    }
}