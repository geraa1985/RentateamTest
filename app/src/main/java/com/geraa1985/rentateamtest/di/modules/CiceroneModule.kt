package com.geraa1985.rentateamtest.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class CiceroneModule {

    @Singleton
    @Provides
    fun cicerone(): Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun navigatorHolder(cicerone: Cicerone<Router>) = cicerone.navigatorHolder

    @Singleton
    @Provides
    fun router(cicerone: Cicerone<Router>) = cicerone.router

}