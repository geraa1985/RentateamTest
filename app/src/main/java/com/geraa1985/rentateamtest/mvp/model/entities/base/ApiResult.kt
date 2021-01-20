package com.geraa1985.rentateamtest.mvp.model.entities.base

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiResult(
    @Expose val data: List<User>
): Parcelable