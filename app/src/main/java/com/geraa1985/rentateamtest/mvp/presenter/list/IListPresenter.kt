package com.geraa1985.rentateamtest.mvp.presenter.list

import com.geraa1985.rentateamtest.mvp.view.lists.IItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}