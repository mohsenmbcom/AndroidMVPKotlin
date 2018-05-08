package com.mohsenmb.kotlintvmaze.model

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import javax.inject.Inject

class ShowsListInteractorImpl @Inject constructor(private val tvMazeWebService: TVMazeWebService) : ShowsListInteractor {

    private var showsDisposable: Disposable? = null
    private var showsReplaySubject: ReplaySubject<List<Show>> = ReplaySubject.create()

    override fun loadShows(page: Int): Observable<List<Show>> {
        if (showsDisposable == null || showsDisposable!!.isDisposed) {
            tvMazeWebService.getShows(page).subscribeOn(Schedulers.io()).subscribe(showsReplaySubject)
            showsDisposable = showsReplaySubject.subscribe({}, { error -> error.printStackTrace() })
        }
        return showsReplaySubject.share()
    }

    override fun destroy() {
        if (!showsDisposable!!.isDisposed) {
            showsDisposable!!.dispose()
        }

        showsDisposable = null
    }

}