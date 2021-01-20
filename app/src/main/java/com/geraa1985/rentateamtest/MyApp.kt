package com.geraa1985.rentateamtest

import android.app.Application
import com.geraa1985.rentateamtest.di.components.DaggerMainGraph
import com.geraa1985.rentateamtest.di.components.MainGraph
import com.geraa1985.rentateamtest.di.modules.AppModule

class MyApp: Application() {

    companion object{
        lateinit var instance: MyApp
    }

    lateinit var mainGraph: MainGraph

    override fun onCreate() {
        super.onCreate()
        instance = this

        mainGraph = DaggerMainGraph.builder()
            .appModule(AppModule(this))
            .build()
    }

}