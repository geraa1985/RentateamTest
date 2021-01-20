package com.geraa1985.rentateamtest.mvp.model.networkstatus

import io.reactivex.rxjava3.core.Single

interface INetworkStatus {
    fun checkConnection(): Single<Boolean>
}