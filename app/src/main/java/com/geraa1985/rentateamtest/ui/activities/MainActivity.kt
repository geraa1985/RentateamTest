package com.geraa1985.rentateamtest.ui.activities

import android.os.Bundle
import com.geraa1985.rentateamtest.MyApp
import com.geraa1985.rentateamtest.R
import com.geraa1985.rentateamtest.databinding.ActivityMainBinding
import com.geraa1985.rentateamtest.mvp.presenter.MainPresenter
import com.geraa1985.rentateamtest.mvp.view.IMainView
import com.geraa1985.rentateamtest.ui.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), IMainView {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator by lazy {
        SupportAppNavigator(this, supportFragmentManager, binding.hostForFragments.id)
    }

    private val presenter: MainPresenter by moxyPresenter {
        MainPresenter().apply { MyApp.instance.mainGraph.inject(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MyApp.instance.mainGraph.inject(this)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_users -> presenter.openUsers().let { true }
                R.id.menu_about -> presenter.openAbout().let { true }
                else -> presenter.openUsers().let { true }
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backClicked()) {
                return
            }
        }
        presenter.backClicked()
    }
}