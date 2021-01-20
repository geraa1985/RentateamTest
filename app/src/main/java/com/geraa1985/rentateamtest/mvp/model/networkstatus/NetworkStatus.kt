package com.geraa1985.rentateamtest.mvp.model.networkstatus

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.net.HttpURLConnection
import java.net.URL

class NetworkStatus(url: String): INetworkStatus {

    private val networkStatus: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    init {
        getInternetStatus(url)
    }

    private fun getInternetStatus(myUrl: String) {
        val url = URL(myUrl)
        Completable.fromAction {
            try {
                val httpUrlConnection = url.openConnection() as HttpURLConnection
                httpUrlConnection.connectTimeout = 3000
                httpUrlConnection.connect()
                if (httpUrlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    networkStatus.onNext(true)
                } else {
                    networkStatus.onNext(false)
                }
            } catch (e: Exception) {
                networkStatus.onNext(false)
            }
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun isOnlineSingle(): Single<Boolean> = networkStatus.first(false)
}