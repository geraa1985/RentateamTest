package com.geraa1985.rentateamtest.mvp.model.repositories

import com.geraa1985.rentateamtest.MyApp
import com.geraa1985.rentateamtest.mvp.model.api.IApiData
import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import com.geraa1985.rentateamtest.mvp.model.entities.room.cache.IUsersCache
import com.geraa1985.rentateamtest.mvp.model.networkstatus.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UsersRepo : IUsersRepo {

    @Inject
    lateinit var networkStatus: INetworkStatus

    @Inject
    lateinit var api: IApiData

    @Inject
    lateinit var usersCache: IUsersCache

    private var totalPages = 0

    init {
        MyApp.instance.mainGraph.inject(this)
    }

    override fun getUsers(page: Int): Single<List<User>> =
        networkStatus.checkConnection().flatMap { isOnline ->
                if (isOnline) {
                    api.getUsers(page).flatMap { apiResult ->
                        totalPages = apiResult.totalPages
                        Single.just(apiResult.data)
                    }
                } else {
                    usersCache.getUsers()
                }
            }.subscribeOn(Schedulers.io())

    override fun getNewPageUsers(page: Int): Single<List<User>>? =
        networkStatus.checkConnection().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers(page).flatMap { apiResult ->
                    totalPages = apiResult.totalPages
                    Single.just(apiResult.data)
                }
            } else {
                null
            }
        }.subscribeOn(Schedulers.io())

    override fun getPages() = totalPages

    override fun putUser(user: User) {
        usersCache.putUser(user)
    }

}