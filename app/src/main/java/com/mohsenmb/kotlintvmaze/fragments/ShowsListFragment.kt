package com.mohsenmb.kotlintvmaze.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohsenmb.kotlintvmaze.*
import com.mohsenmb.kotlintvmaze.component.ShowsListAdapter
import com.mohsenmb.kotlintvmaze.di.ShowsListModule
import com.mohsenmb.kotlintvmaze.model.Show
import com.mohsenmb.kotlintvmaze.presenter.ShowsListPresenter
import com.mohsenmb.kotlintvmaze.view.ShowsListView
import kotlinx.android.synthetic.main.fragment_shows_list.*
import java.util.ArrayList
import javax.inject.Inject

class ShowsListFragment : BaseFragment(), ShowsListView {
    @Inject
    lateinit var showsListPresenter: ShowsListPresenter

    var page: Int = 1
    var loadMore: Boolean = true
    private val shows: MutableList<Show> = ArrayList()
    private val showsAdapter: ShowsListAdapter = ShowsListAdapter(shows)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity!!.application as TvMazeApplication).getComponent()!!.plus(ShowsListModule(this)).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return makeContentView(inflater, R.layout.fragment_shows_list, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvShows.adapter = showsAdapter
        rvShows.layoutManager = LinearLayoutManager(context)
        rvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager: LinearLayoutManager = recyclerView!!.layoutManager as LinearLayoutManager
                if (layoutManager.findLastVisibleItemPosition() == recyclerView.adapter.itemCount - 1) {
                    if (loadMore) {
                        loadShows()
                    }
                }
            }
        })
        srlShows.setOnRefreshListener {
            page = 1
            loadShows()
        }
        loadShows()
    }

    private fun loadShows() {
        showsListPresenter.loadShows(activity!!.isConnected(), page)

    }

    override fun toggleProgress(show: Boolean) {
        srlShows.isRefreshing = show
    }

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun showRetry() {
        val retrySnackbar = Snackbar.make(view!!,
                R.string.cant_get_data_please_try_again, Snackbar.LENGTH_SHORT)
        retrySnackbar.setAction(R.string.retry, {})
    }

    override fun showOfflineMessage() {
        showToast(getString(R.string.you_are_offline_check_settings))
    }

    override fun displayShowsList(shows: List<Show>) {
        if (shows.isEmpty()) {
            loadMore = false
        } else {
            loadMore = true
            if (page == 1) {
                this.shows.clear()
                this.showsAdapter.notifyDataSetChanged()
            }
            val lastSize = this.shows.size
            this.shows.addAll(shows)
            this.showsAdapter.notifyItemRangeInserted(lastSize, shows.size)
            showToast(getString(R.string.page_loaded, page))
        }
        page++
    }

    override fun onDestroy() {
        showsListPresenter.destroy()
        super.onDestroy()
    }
}