package com.geraa1985.rentateamtest.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onStart() {
        super.onStart()
        binding.rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = (recyclerView.layoutManager as LinearLayoutManager).childCount
                val totalItemCount = (recyclerView.layoutManager as LinearLayoutManager).itemCount
                val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                presenter.loadPage(visibleItemCount,totalItemCount,firstVisibleItem)
            }
        })
    }

    override fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressCircular.visibility = View.INVISIBLE
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