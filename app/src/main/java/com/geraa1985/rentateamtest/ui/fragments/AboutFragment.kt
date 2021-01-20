package com.geraa1985.rentateamtest.ui.fragments

import com.geraa1985.rentateamtest.MyApp
import com.geraa1985.rentateamtest.R
import com.geraa1985.rentateamtest.mvp.presenter.AboutPresenter
import com.geraa1985.rentateamtest.mvp.view.IAboutView
import com.geraa1985.rentateamtest.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AboutFragment: MvpAppCompatFragment(R.layout.fragment_about), IAboutView, BackButtonListener {

    private val presenter: AboutPresenter by moxyPresenter {
        AboutPresenter().apply { MyApp.instance.mainGraph.inject(this) }
    }

    override fun backClicked(): Boolean {
        return presenter.backClicked()
    }
}