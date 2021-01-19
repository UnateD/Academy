package com.unated.academy.data

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("backdrop_path")
    val backdrop: String,
    @SerializedName("vote_average")
    val ratings: Float,
    @SerializedName("vote_count")
    val numberOfRatings: Int,
    val adult: Boolean,
    @SerializedName("genre_ids")
    val genres: List<Int>,
    val isFavorite: Boolean,
    var displayGenres: ArrayList<Genre>,
    var runtime: Int
)