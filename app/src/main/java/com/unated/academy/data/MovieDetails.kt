package com.unated.academy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "details")
data class MovieDetails(
    @PrimaryKey
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
    val genres: ArrayList<Genre>,
    var actors: ArrayList<Actor>,
    val isFavorite: Boolean,
    var runtime: Int
)