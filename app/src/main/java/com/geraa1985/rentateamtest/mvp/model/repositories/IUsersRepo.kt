package com.geraa1985.rentateamtest.mvp.model.repositories

import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface IUsersRepo {
    fun getUsers(page: Int): Single<List<User>>
    fun getNewPageUsers(page: Int): Single<List<User>>?
    fun putUser(user: User)
    fun getPages(): Int
}