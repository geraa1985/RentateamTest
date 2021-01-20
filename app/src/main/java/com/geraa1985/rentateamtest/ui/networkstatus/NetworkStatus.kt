package com.geraa1985.rentateamtest.ui.networkstatus

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.geraa1985.rentateamtest.mvp.model.networkstatus.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class NetworkStatus(context: Context) : INetworkStatus {

    private val networkStatus: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    init {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities)
            actNw?.let {
                when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> networkStatus.onNext(true)
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> networkStatus.onNext(true)
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> networkStatus.onNext(true)
                    else -> networkStatus.onNext(false)
                }
            } ?: networkStatus.onNext(false)
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    when (type) {
                        ConnectivityManager.TYPE_WIFI -> networkStatus.onNext(true)
                        ConnectivityManager.TYPE_MOBILE -> networkStatus.onNext(true)
                        ConnectivityManager.TYPE_ETHERNET -> networkStatus.onNext(true)
                        else -> networkStatus.onNext(false)
                    }

                }
            }
        }
    }

    override fun isOnlineSingle(): Single<Boolean> = networkStatus.first(false)
}
