package com.geraa1985.rentateamtest.mvp.model.entities.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomUser (
    @PrimaryKey val id: Int,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val avatar: String?
)