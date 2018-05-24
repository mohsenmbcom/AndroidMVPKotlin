package com.mohsenmb.kotlintvmaze.component

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mohsenmb.kotlintvmaze.BR
import com.mohsenmb.kotlintvmaze.R
import com.mohsenmb.kotlintvmaze.model.Show

class ShowsListAdapter(private val showsList: List<Show>) : RecyclerView.Adapter<DataBindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.row_show, parent, false)

        return DataBindingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return showsList.size
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        val show: Show = showsList[position]
        holder.bindVariable(BR.show, show)
    }

}