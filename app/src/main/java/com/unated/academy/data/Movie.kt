package com.unated.academy.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor

@Entity(tableName = "movies")
data class Movie(
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
    @SerializedName("genre_ids")
    val genres: ArrayList<Int>,
    val isFavorite: Boolean,
    var displayGenres: ArrayList<Genre>,
    var runtime: Int
)