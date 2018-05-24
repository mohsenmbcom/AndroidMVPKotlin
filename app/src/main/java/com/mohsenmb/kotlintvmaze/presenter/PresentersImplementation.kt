package com.mohsenmb.kotlintvmaze.presenter

import com.mohsenmb.kotlintvmaze.model.ShowsListInteractor
import com.mohsenmb.kotlintvmaze.view.ShowsListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class ShowsListPresenterImpl @Inject constructor(private val showsListInteractor: ShowsListInteractor) : ShowsListPresenter {
    lateinit var showsListView: ShowsListView

    private lateinit var disposable: Disposable

    override fun loadShows(isConnected: Boolean, page: Int) {
        showsListView.toggleProgress(true)
        disposable = showsListInteractor
                .loadShows(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ showItems ->
                    showsListView.toggleProgress(false)
                    showsListView.displayShowsList(showItems)
                }, {
                    showsListView.toggleProgress(false)

                    if (isConnected) {
                        showsListView.showRetry()
                    } else {
                        showsListView.showOfflineMessage()
                    }
                })
    }

    override fun setView(listView: ShowsListView) {
        this.showsListView = listView
    }

    override fun destroy() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        showsListInteractor.destroy()
    }
}