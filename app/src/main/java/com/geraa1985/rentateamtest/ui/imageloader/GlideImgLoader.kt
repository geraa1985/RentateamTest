package com.geraa1985.rentateamtest.ui.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.geraa1985.rentateamtest.mvp.model.repositories.ILoadImage

class GlideImgLoader: ILoadImage<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide
            .with(container.context)
            .load(url)
            .into(container)
    }
}