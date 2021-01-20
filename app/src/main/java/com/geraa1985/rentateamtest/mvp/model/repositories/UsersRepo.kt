package com.geraa1985.rentateamtest.mvp.model.repositories

import com.geraa1985.rentateamtest.MyApp
import com.geraa1985.rentateamtest.mvp.model.api.IApiData
import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import com.geraa1985.rentateamtest.mvp.model.entities.room.cache.IUsersCache
import com.geraa1985.rentateamtest.mvp.model.networkstatus.INetworkStatus
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UsersRepo : IUsersRepo{

    @Inject
    lateinit var networkStatus: INetworkStatus
    @Inject
    lateinit var api: IApiData
    @Inject
    lateinit var usersCache: IUsersCache


    init {
        MyApp.instance.mainGraph.inject(this)
    }


    override fun getUsers(): Single<List<User>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUsers().flatMap { apiResult ->
                    Single.just(apiResult.data)
                }
            } else {
                usersCache.getUsers()
            }
        }.subscribeOn(Schedulers.io())


    override fun putUser(user: User) {
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                usersCache.putUser(user)
            }
        }
    }

}