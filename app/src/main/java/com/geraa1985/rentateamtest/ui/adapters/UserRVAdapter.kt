package com.geraa1985.rentateamtest.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.geraa1985.rentateamtest.R
import com.geraa1985.rentateamtest.databinding.ItemRvUsersBinding
import com.geraa1985.rentateamtest.mvp.presenter.list.IUserListPresenter
import com.geraa1985.rentateamtest.mvp.view.lists.IUserItemView

class UserRVAdapter(
    private val presenter: IUserListPresenter
): RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvUsersBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.getItem().setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.getItem().context, R.anim.rv_users_anim)
    }

    inner class ViewHolder(private val binding: ItemRvUsersBinding) :
        RecyclerView.ViewHolder(binding.root), IUserItemView {

        override var pos = 0

        override fun setFullName(fullName: String) {
            binding.tvName.text = fullName
        }

        fun getItem() = binding.root
    }
}