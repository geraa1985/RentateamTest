package com.geraa1985.rentateamtest.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.geraa1985.rentateamtest.mvp.model.entities.base.User

class UserDiffUtilCallback(
    private val oldList: List<User>,
    private val newList: List<User>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.firstName.equals(newUser.firstName) && oldUser.lastName.equals(newUser.lastName) && oldUser.email.equals(newUser.email)
    }
}