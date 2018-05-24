package com.mohsenmb.kotlintvmaze.component

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.mohsenmb.kotlintvmaze.toUri
import com.squareup.picasso.Picasso

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        Picasso
                .get()
                .load(url.toUri())
                .centerCrop()
                .fit()
                .into(view)
    }
}