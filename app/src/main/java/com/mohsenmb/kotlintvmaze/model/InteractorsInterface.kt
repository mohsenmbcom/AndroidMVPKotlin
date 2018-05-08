package com.mohsenmb.kotlintvmaze.model

import io.reactivex.Observable

interface BaseInteractor {
    fun destroy()
}

interface ShowsListInteractor : BaseInteractor {
    fun loadShows(page: Int): Observable<List<Show>>
}