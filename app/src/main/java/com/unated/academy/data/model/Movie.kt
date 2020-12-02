package com.unated.academy.data.model

data class Movie(
    var id: Int,
    var smallCoverImg: Int,
    var fullCoverImg: Int,
    var genre: String,
    var rating: Float,
    var reviewsCount: Int,
    var title: String,
    var duration: Int,
    var ageRating: String,
    var isFavorite: Boolean,
    var storyline: String,
    var actors: ArrayList<Actor>
)