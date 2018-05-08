package com.mohsenmb.kotlintvmaze.model

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TVMazeWebService {
    @GET("/shows")
    fun getShows(@Query("page") page: Int): Observable<List<Show>>
}