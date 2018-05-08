package com.mohsenmb.kotlintvmaze.presenter

import com.mohsenmb.kotlintvmaze.view.BaseView
import com.mohsenmb.kotlintvmaze.view.ShowsListView

interface BasePresenter<in T : BaseView> {
    fun destroy()

    fun setView(view: T)
}

interface ShowsListPresenter : BasePresenter<ShowsListView> {
    fun loadShows(isConnected: Boolean, page: Int)
}