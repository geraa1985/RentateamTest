package com.geraa1985.rentateamtest.mvp.model.entities.base

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    @Expose val id: Int,
    @Expose val email: String? = null,
    @Expose val firstName: String? = null,
    @Expose val lastName: String? = null,
    @Expose val avatar: String? = null
) : Parcelable