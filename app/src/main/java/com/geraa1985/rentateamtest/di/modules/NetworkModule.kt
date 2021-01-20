package com.geraa1985.rentateamtest.di.modules

import com.geraa1985.rentateamtest.mvp.model.api.IApiData
import com.geraa1985.rentateamtest.mvp.model.networkstatus.INetworkStatus
import com.geraa1985.rentateamtest.mvp.model.networkstatus.NetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://reqres.in"

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IApiData =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IApiData::class.java)

    @Singleton
    @Provides
    fun networkStatus(@Named("baseUrl") baseUrl: String): INetworkStatus = NetworkStatus(baseUrl)

}