package com.unated.academy.domain

import com.unated.academy.R
import com.unated.academy.model.Actor
import com.unated.academy.model.Movie
import java.util.*

class DataSource {

    fun getMovies(): ArrayList<Movie> {
        return arrayListOf(
            Movie(
                "Avengers: End Game".hashCode(),
                smallCoverImg = R.drawable.bg_small_avengers,
                fullCoverImg = 0,
                genre = "Action, Adventure, Drama",
                rating = 4f,
                reviewsCount = 125,
                title = "Avengers: End Game",
                duration = 137,
                ageRating = "13+",
                isFavorite = false,
                storyline = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\\' actions and restore balance to the universe.",
                actors = getActors()
            ),
            Movie(
                "Tenet".hashCode(),
                smallCoverImg = R.drawable.bg_small_tenet,
                fullCoverImg = 0,
                genre = "Action, Sci-Fi, Thriller ",
                rating = 5f,
                reviewsCount = 98,
                title = "Tenet",
                duration = 97,
                ageRating = "16+",
                isFavorite = true,
                storyline = "No data",
                actors = arrayListOf()
            ),
            Movie(
                "Black Widow".hashCode(),
                smallCoverImg = R.drawable.bg_small_widow,
                fullCoverImg = 0,
                genre = "Action, Adventure, Sci-Fi",
                rating = 4f,
                reviewsCount = 38,
                title = "Black Widow",
                duration = 102,
                ageRating = "13+",
                isFavorite = false,
                storyline = "No data",
                actors = arrayListOf()
            ),
            Movie(
                "Wonder Woman 1984".hashCode(),
                smallCoverImg = R.drawable.bg_small_ww,
                fullCoverImg = 0,
                genre = "Action, Adventure, Fantasy",
                rating = 5f,
                reviewsCount = 74,
                title = "Wonder Woman 1984",
                duration = 120,
                ageRating = "13+",
                isFavorite = false,
                storyline = "No data",
                actors = arrayListOf()
            )
        )
    }

    fun getMovieById(id: Int): Movie? = getMovies().find { it.id == id }

    private fun getActors(): ArrayList<Actor> {
        return arrayListOf(
            Actor("Robert Downey Jr.", R.drawable.img_downey),
            Actor("Chris Evans", R.drawable.img_evans),
            Actor("Mark Ruffalo", R.drawable.img_ruffalo),
            Actor("Chris Hemsworth", R.drawable.img_hemsworth)
        )
    }
}