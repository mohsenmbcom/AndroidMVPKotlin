package com.mohsenmb.kotlintvmaze.view

import com.mohsenmb.kotlintvmaze.model.Show

interface BaseView {
    fun toggleProgress(show: Boolean)
    fun showMessage(message: String)
    fun showRetry()
    fun showOfflineMessage()
}

interface ShowsListView : BaseView {
    fun displayShowsList(shows: List<Show>)
}