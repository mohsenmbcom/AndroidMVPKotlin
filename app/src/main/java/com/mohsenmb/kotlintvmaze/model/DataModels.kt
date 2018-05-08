package com.mohsenmb.kotlintvmaze.model

import com.google.gson.annotations.Expose

data class Show(@Expose var id: Int, @Expose var name: String, @Expose var image: ShowImage)

data class ShowImage(@Expose var medium: String, @Expose var original: String)