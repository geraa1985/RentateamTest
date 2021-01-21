package com.geraa1985.rentateamtest.mvp.presenter

import com.geraa1985.rentateamtest.MyApp
import com.geraa1985.rentateamtest.mvp.model.entities.base.User
import com.geraa1985.rentateamtest.mvp.model.repositories.IUsersRepo
import com.geraa1985.rentateamtest.mvp.presenter.list.IUserListPresenter
import com.geraa1985.rentateamtest.mvp.view.IUsersListView
import com.geraa1985.rentateamtest.mvp.view.lists.IUserItemView
import com.geraa1985.rentateamtest.navigation.FragmentScreen
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersListPresenter : MvpPresenter<IUsersListView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var usersRepo: IUsersRepo

    @Inject
    lateinit var uiScheduler: Scheduler

    init {
        MyApp.instance.mainGraph.inject(this)
    }

    private var currentPage = 1
    private val totalPages: Int by lazy { usersRepo.getPages() }

    class ItemListPresenter : IUserListPresenter {

        val users = mutableListOf<User>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            val firstName = user.firstName
            val lastName = user.lastName
            view.setFullName("$firstName $lastName")
        }
    }

    val itemListPresenter = ItemListPresenter()
    private val compositeDisposable = CompositeDisposable()

    private val subject: PublishSubject<User> = PublishSubject.create()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRvUsers()
        loadData()

        subject.observeOn(Schedulers.io()).subscribe { user ->
            usersRepo.putUser(user)
        }

        itemListPresenter.itemClickListener = {
            val user = itemListPresenter.users[it.pos]
            subject.onNext(user)
            router.navigateTo(FragmentScreen.userScreen(user))
        }
    }

    private fun loadData() {
        viewState.showProgress()
        val disposable1 = usersRepo.getUsers(currentPage)
            .observeOn(uiScheduler)
            .subscribe({
                val oldList = mutableListOf<User>()
                oldList.addAll(itemListPresenter.users)
                itemListPresenter.users.addAll(it)
                viewState.hideProgress()
                viewState.updateUsersList(oldList, itemListPresenter.users)
            }, { error ->
                error.message?.let {
                    viewState.showError(it)
                }
            })
        compositeDisposable.add(disposable1)
    }

    fun loadPage(visibleItemCount: Int, totalItemCount: Int, firstVisibleItem: Int) {
        if ((visibleItemCount+firstVisibleItem) >= totalItemCount && currentPage < totalPages) {
            val disposable2 = usersRepo.getUsers(++currentPage)
                .observeOn(uiScheduler)
                .subscribe({
                    val oldList = mutableListOf<User>()
                    oldList.addAll(itemListPresenter.users)
                    itemListPresenter.users.addAll(it)
                    viewState.updateUsersList(oldList, itemListPresenter.users)
                }, { error ->
                    error.message?.let {
                        viewState.showError(it)
                    }
                })
            compositeDisposable.add(disposable2)
        }
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}