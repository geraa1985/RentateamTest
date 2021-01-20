package com.geraa1985.rentateamtest.di.components

import com.geraa1985.rentateamtest.di.modules.*
import com.geraa1985.rentateamtest.mvp.model.entities.room.cache.UsersCache
import com.geraa1985.rentateamtest.mvp.model.repositories.UsersRepo
import com.geraa1985.rentateamtest.mvp.presenter.AboutPresenter
import com.geraa1985.rentateamtest.mvp.presenter.UsersListPresenter
import com.geraa1985.rentateamtest.mvp.presenter.MainPresenter
import com.geraa1985.rentateamtest.mvp.presenter.UserPresenter
import com.geraa1985.rentateamtest.ui.activities.MainActivity
import com.geraa1985.rentateamtest.ui.adapters.UserRVAdapter
import com.geraa1985.rentateamtest.ui.fragments.UserFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        NetworkModule::class,
        ReposModule::class,
        AppModule::class,
        CacheModule::class
    ]
)
interface MainGraph {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersListPresenter: UsersListPresenter)
    fun inject(usersPresenter: UserPresenter)
    fun inject(usersFragment: UserFragment)
    fun inject(aboutPresenter: AboutPresenter)
    fun inject(usersRepo: UsersRepo)
    fun inject(usersCache: UsersCache)
    fun inject(usersRVAdapter: UserRVAdapter)
}