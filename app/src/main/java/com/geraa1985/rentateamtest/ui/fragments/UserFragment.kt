package com.geraa1985.rentateamtest.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.geraa1985.rentateamtest.MyApp
import com.geraa1985.rentateamtest.databinding.FragmentUserBinding
import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import com.geraa1985.rentateamtest.mvp.model.repositories.ILoadImage
import com.geraa1985.rentateamtest.mvp.presenter.UserPresenter
import com.geraa1985.rentateamtest.mvp.view.IUserView
import com.geraa1985.rentateamtest.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserFragment: MvpAppCompatFragment(), IUserView, BackButtonListener {

    companion object {
        private const val USER_KEY = "user"

        fun newInstance(user: User) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_KEY, user)
            }
        }
    }

    private lateinit var binding: FragmentUserBinding

    @Inject
    lateinit var imgLoader: ILoadImage<ImageView>

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter().apply { MyApp.instance.mainGraph.inject(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyApp.instance.mainGraph.inject(this)
    }

    override fun setUser() {
        val user: User? = arguments?.getParcelable(USER_KEY)
        presenter.setUser(user)
    }

    override fun showAvatar(url: String) {
        imgLoader.loadInto(url, binding.avatar)
    }

    override fun showFirstName(firstName: String) {
        binding.toolbar.title = firstName
        binding.firstName.text = firstName
    }

    override fun showLastName(lastName: String) {
        binding.lastName.text = lastName
    }

    override fun showEmail(email: String) {
        binding.email.text = email
    }

    @SuppressLint("ShowToast")
    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
    }

    override fun backClicked(): Boolean {
        return presenter.backClicked()
    }
}