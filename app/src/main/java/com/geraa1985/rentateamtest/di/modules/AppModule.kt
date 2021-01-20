package com.geraa1985.rentateamtest.di.modules

import com.geraa1985.rentateamtest.MyApp
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(private val app: MyApp) {

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun app(): MyApp {
        return app
    }
}