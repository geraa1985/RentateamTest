package com.geraa1985.rentateamtest.mvp.model.api

import com.geraa1985.rentateamtest.mvp.model.entities.base.ApiResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiData {

    @GET("/api/users")
    fun getUsers(@Query("page") page: Int): Single<ApiResult>

}