package com.geraa1985.rentateamtest.mvp.model.entities.room.dao

import androidx.room.*
import com.geraa1985.rentateamtest.mvp.model.entities.room.entities.RoomUser

@Dao
interface IUsersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomUser>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomUser)

    @Update
    fun update(user: RoomUser)

    @Update
    fun update(users: List<RoomUser>)

    @Update
    fun update(vararg users: RoomUser)

    @Delete
    fun delete(user: RoomUser)

    @Delete
    fun delete(users: List<RoomUser>)

    @Delete
    fun delete(vararg users: RoomUser)

    @Query("SELECT * FROM RoomUser")
    fun getAll(): List<RoomUser>

    @Query("SELECT * FROM RoomUser WHERE firstName = :firstName LIMIT 1")
    fun getUser(firstName: String): RoomUser?

    @Query("SELECT * FROM RoomUser WHERE firstName LIKE '%' || :firstName  || '%'")
    fun getUsersByName(firstName: String): List<RoomUser>
}