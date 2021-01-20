package com.geraa1985.rentateamtest.mvp.model.entities.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geraa1985.rentateamtest.mvp.model.entities.room.dao.IUsersDAO
import com.geraa1985.rentateamtest.mvp.model.entities.room.entities.RoomUser

@Database(entities = [RoomUser::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract val userDAO: IUsersDAO

    companion object {
        const val NAME_DB = "database.db"
    }
}