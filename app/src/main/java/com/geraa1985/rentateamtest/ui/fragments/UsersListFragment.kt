package com.geraa1985.rentateamtest.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.geraa1985.rentateamtest.MyApp
import com.geraa1985.rentateamtest.databinding.FragmentUsersListBinding
import com.geraa1985.rentateamtest.mvp.presenter.UsersListPresenter
import com.geraa1985.rentateamtest.mvp.view.IUsersListView
import com.geraa1985.rentateamtest.ui.BackButtonListener
import com.geraa1985.rentateamtest.ui.adapters.UserRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersListFragment: MvpAppCompatFragment(), IUsersListView, BackButtonListener {

    private lateinit var binding: FragmentUsersListBinding

    private var adapter: UserRVAdapter? = null

    private val presenter: UsersListPresenter by moxyPresenter {
        UsersListPresenter().apply { MyApp.instance.mainGraph.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initRvUsers() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
    }

    override fun updateUsersList() {
        adapter?.notifyDataSetChanged()
    }

    @SuppressLint("ShowToast")
    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun backClicked(): Boolean {
        return presenter.backClicked()
    }


}