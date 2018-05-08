package com.mohsenmb.kotlintvmaze

import android.app.Activity
import android.content.Context
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mohsenmb.kotlintvmaze.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_base.view.*
import android.net.ConnectivityManager



fun BaseFragment.toggleProgress(show: Boolean) {
    progressBar.visibility = if (show) View.VISIBLE else View.GONE
}

fun BaseFragment.makeContentView(layoutInflater: LayoutInflater, @LayoutRes layout: Int, container: ViewGroup?): View {
    val rootView = layoutInflater.inflate(R.layout.fragment_base, container, false)
    rootView.viewStub.layoutResource = layout
    rootView.viewStub.inflate()
    progressBar = rootView.progressBar
    return rootView
}

fun BaseFragment.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Activity.isConnected() : Boolean{
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}
