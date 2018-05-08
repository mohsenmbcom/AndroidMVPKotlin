package com.mohsenmb.kotlintvmaze.di

import com.mohsenmb.kotlintvmaze.fragments.ShowsListFragment
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class)])
public interface MainComponent {
    fun plus(showsListModule: ShowsListModule): ShowsListComponent
}

@Subcomponent(modules = [(ShowsListModule::class)])
public interface ShowsListComponent {
    fun inject(showsListFragment: ShowsListFragment)
}