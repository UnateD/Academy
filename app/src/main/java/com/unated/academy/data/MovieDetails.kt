package com.unated.academy.data

import com.google.gson.annotations.SerializedName

data class MovieDetails(
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
    val genres: List<Genre>,
    var actors: ArrayList<Actor>,
    val isFavorite: Boolean,
    var runtime: Int
)