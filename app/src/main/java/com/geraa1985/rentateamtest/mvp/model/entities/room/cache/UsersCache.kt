package com.geraa1985.rentateamtest.mvp.model.entities.room.cache

import com.geraa1985.rentateamtest.MyApp
import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import com.geraa1985.rentateamtest.mvp.model.entities.room.db.AppDB
import com.geraa1985.rentateamtest.mvp.model.entities.room.entities.RoomUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UsersCache : IUsersCache {

    @Inject
    lateinit var db: AppDB

    init {
        MyApp.instance.mainGraph.inject(this)
    }

    override fun putUsers(users: List<User>) {
        val roomUsers = users.map {
            RoomUser(
                it.id,
                it.firstName ?: "",
                it.lastName ?: "",
                it.email ?: "",
                it.avatar ?: ""
            )
        }
        db.userDAO.insert(roomUsers)
    }

    override fun getUsers(): Single<List<User>>  =
        Single.just(db.userDAO.getAll().map {
            User(
                it.id,
                it.email ?: "",
                it.firstName ?: "",
                it.lastName ?: "",
                it.avatar ?: ""
            )
        })

    override fun putUser(user: User) {
        val roomUser = user.let {
            RoomUser(
                it.id,
                it.firstName ?: "",
                it.lastName ?: "",
                it.email ?: "",
                it.avatar ?: ""
            )
        }
        db.userDAO.insert(roomUser)
    }

    override fun getUser(login: String): Single<User> =
        Single.just(db.userDAO.getUser(login)?.let {
            User(
                it.id,
                it.email ?: "",
                it.firstName ?: "",
                it.lastName ?: "",
                it.avatar ?: ""
            )
        })

    override fun getUsersByName(name: String): Single<List<User>> =
        Single.just(db.userDAO.getUsersByName(name).map {
            User(
                it.id,
                it.email ?: "",
                it.firstName ?: "",
                it.lastName ?: "",
                it.avatar ?: ""
            )
        })
}