package com.geraa1985.rentateamtest.mvp.model.entities.room.cache

import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import io.reactivex.rxjava3.core.Single

interface IUsersCache {
    fun putUsers(users: List<User>)
    fun getUsers(): Single<List<User>>
    fun putUser(user: User)
    fun getUser(login: String): Single<User>
    fun getUsersByName(name: String): Single<List<User>>
}