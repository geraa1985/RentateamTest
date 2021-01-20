package com.geraa1985.rentateamtest.mvp.model.networkstatus

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.HttpURLConnection
import java.net.URL

class NetworkStatus(private val url: String): INetworkStatus {

    override fun checkConnection(): Single<Boolean> =
        getInternetStatus(url).subscribeOn(Schedulers.io())


    private fun getInternetStatus(myUrl: String): Single<Boolean> {
        val url = URL(myUrl)
        return Single.fromCallable {
            try {
                val httpUrlConnection = url.openConnection() as HttpURLConnection
                httpUrlConnection.connectTimeout = 3000
                httpUrlConnection.connect()
                if (httpUrlConnection.responseCode == HttpURLConnection.HTTP_OK) {
                    return@fromCallable true
                }
            } catch (e: Exception) {
                return@fromCallable false
            }
            return@fromCallable false
        }
    }

}