package com.mohsenmb.kotlintvmaze.activities

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.mohsenmb.kotlintvmaze.R
import com.mohsenmb.kotlintvmaze.fragments.ShowsListFragment

class MainActivity : BaseActivity() {

    private val showsListFragment: ShowsListFragment = ShowsListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.container, showsListFragment)
                .hide(showsListFragment)
                .commit()
        showShowsListFragment()
    }

    private fun showShowsListFragment() {
        supportFragmentManager
                .beginTransaction()
                .show(showsListFragment)
                .commit()
    }
}
