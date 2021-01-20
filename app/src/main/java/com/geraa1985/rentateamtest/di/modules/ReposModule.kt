package com.geraa1985.rentateamtest.di.modules

import android.widget.ImageView
import com.geraa1985.rentateamtest.mvp.model.repositories.ILoadImage
import com.geraa1985.rentateamtest.mvp.model.repositories.IUsersRepo
import com.geraa1985.rentateamtest.mvp.model.repositories.UsersRepo
import com.geraa1985.rentateamtest.ui.imageloader.GlideImgLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposModule {

    @Singleton
    @Provides
    fun imgRepo(): ILoadImage<ImageView> = GlideImgLoader()

    @Singleton
    @Provides
    fun usersRepo(): IUsersRepo = UsersRepo()
}