package com.mohsenmb.kotlintvmaze.component

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mohsenmb.kotlintvmaze.R
import com.mohsenmb.kotlintvmaze.model.Show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_show.view.*

class ShowsListAdapter(private val showsList: List<Show>) : RecyclerView.Adapter<ShowsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsListViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        return ShowsListViewHolder(layoutInflater.inflate(R.layout.row_show, parent, false))
    }

    override fun getItemCount(): Int {
        return showsList.size
    }

    override fun onBindViewHolder(holder: ShowsListViewHolder, position: Int) {
        val show: Show = showsList[position]
        holder.tvShowTitle.text = show.name
        Picasso.get().load(show.image.medium).into(holder.imgShowCover)
    }

}

class ShowsListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val tvShowTitle: TextView = itemView!!.tvShowTitle
    val imgShowCover: ImageView = itemView!!.imgShowCover
}