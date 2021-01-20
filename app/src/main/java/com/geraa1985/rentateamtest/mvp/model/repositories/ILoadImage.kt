package com.geraa1985.rentateamtest.mvp.model.repositories

interface ILoadImage<T> {
    fun loadInto(url: String, container: T)
}