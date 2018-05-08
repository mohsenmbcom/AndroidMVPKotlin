package com.mohsenmb.kotlintvmaze

import android.app.Application
import com.mohsenmb.kotlintvmaze.di.DaggerMainComponent
import com.mohsenmb.kotlintvmaze.di.MainComponent

class TvMazeApplication : Application() {
    private var component: MainComponent? = null

    fun getComponent(): MainComponent? {
        return component
    }

    override fun onCreate() {
        super.onCreate()

        component = createComponent()
    }

    private fun createComponent(): MainComponent {
        return DaggerMainComponent
                .builder()
                .build()
    }
}