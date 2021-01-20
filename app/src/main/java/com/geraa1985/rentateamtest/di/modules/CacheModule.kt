package com.geraa1985.rentateamtest.di.modules

import androidx.room.Room
import com.geraa1985.rentateamtest.MyApp
import com.geraa1985.rentateamtest.mvp.model.entities.room.cache.IUsersCache
import com.geraa1985.rentateamtest.mvp.model.entities.room.cache.UsersCache
import com.geraa1985.rentateamtest.mvp.model.entities.room.db.AppDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun db(app: MyApp): AppDB = Room.databaseBuilder(
        app,
        AppDB::class.java,
        AppDB.NAME_DB
    ).build()

    @Singleton
    @Provides
    fun usersCache(): IUsersCache = UsersCache()
}