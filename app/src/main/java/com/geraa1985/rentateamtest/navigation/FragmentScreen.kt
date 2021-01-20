package com.geraa1985.rentateamtest.navigation

import androidx.fragment.app.Fragment
import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import com.geraa1985.rentateamtest.ui.fragments.AboutFragment
import com.geraa1985.rentateamtest.ui.fragments.UserFragment
import com.geraa1985.rentateamtest.ui.fragments.UsersListFragment
import moxy.MvpAppCompatFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentScreen(private val fragment: MvpAppCompatFragment): SupportAppScreen() {

    override fun getFragment(): Fragment {
        return fragment
    }

    companion object{
        fun usersListScreen() = FragmentScreen(UsersListFragment())
        fun userScreen(user: User) = FragmentScreen(UserFragment.newInstance(user))
        fun aboutScreen() = FragmentScreen(AboutFragment())
    }

}