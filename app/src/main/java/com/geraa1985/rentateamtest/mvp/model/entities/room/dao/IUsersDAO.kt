package com.geraa1985.rentateamtest.mvp.model.entities.room.dao

import androidx.room.*
import com.geraa1985.rentateamtest.mvp.model.entities.room.entities.RoomUser

@Dao
interface IUsersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomUser)

    @Query("SELECT * FROM RoomUser")
    fun getAll(): List<RoomUser>

}