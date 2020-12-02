package com.unated.academy.domain

import com.unated.academy.R
import com.unated.academy.data.model.Actor
import com.unated.academy.data.model.Movie

fun provideDataSource(): DataSource = DataSourceImpl()

interface DataSource {
    fun getMovies(): ArrayList<Movie>
    fun getMovieById(id: Int): Movie?
}

private class DataSourceImpl : DataSource {
    override fun getMovies(): ArrayList<Movie> {
        return arrayListOf(
            Movie(
                id = "Avengers: End Game".hashCode(),
                smallCoverImg = R.drawable.bg_small_avengers,
                fullCoverImg = R.drawable.header,
                genre = "Action, Adventure, Drama",
                rating = 4f,
                reviewsCount = 125,
                title = "Avengers: End Game",
                duration = 137,
                ageRating = "13+",
                isFavorite = false,
                storyline = "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\\' actions and restore balance to the universe.",
                actors = getActors("Avengers: End Game".hashCode())
            ),
            Movie(
                id = "Tenet".hashCode(),
                smallCoverImg = R.drawable.bg_small_tenet,
                fullCoverImg = R.drawable.bg_tenet,
                genre = "Action, Sci-Fi, Thriller ",
                rating = 5f,
                reviewsCount = 98,
                title = "Tenet",
                duration = 97,
                ageRating = "16+",
                isFavorite = true,
                storyline = "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                actors = getActors("Tenet".hashCode())
            ),
            Movie(
                id = "Black Widow".hashCode(),
                smallCoverImg = R.drawable.bg_small_widow,
                fullCoverImg = R.drawable.bg_widow_large,
                genre = "Action, Adventure, Sci-Fi",
                rating = 4f,
                reviewsCount = 38,
                title = "Black Widow",
                duration = 102,
                ageRating = "13+",
                isFavorite = false,
                storyline = "Natasha Romanoff, also known as Black Widow, confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises. Pursued by a force that will stop at nothing to bring her down, Natasha must deal with her history as a spy and the broken relationships left in her wake long before she became an Avenger.",
                actors = getActors("Black Widow".hashCode())
            ),
            Movie(
                id = "Wonder Woman 1984".hashCode(),
                smallCoverImg = R.drawable.bg_small_ww,
                fullCoverImg = R.drawable.bg_ww,
                genre = "Action, Adventure, Fantasy",
                rating = 5f,
                reviewsCount = 74,
                title = "Wonder Woman 1984",
                duration = 120,
                ageRating = "13+",
                isFavorite = false,
                storyline = "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                actors = getActors("Wonder Woman 1984".hashCode())
            )
        )
    }

    override fun getMovieById(id: Int): Movie? = getMovies().find { it.id == id }

    private fun getActors(): ArrayList<Actor> {
        return arrayListOf(
            Actor("Avengers: End Game".hashCode(), "Robert Downey Jr.", R.drawable.img_downey),
            Actor("Avengers: End Game".hashCode(),"Chris Evans", R.drawable.img_evans),
            Actor("Avengers: End Game".hashCode(),"Mark Ruffalo", R.drawable.img_ruffalo),
            Actor("Avengers: End Game".hashCode(), "Chris Hemsworth", R.drawable.img_hemsworth),
            Actor("Tenet".hashCode(), "John David Washington", R.drawable.img_washington),
            Actor("Tenet".hashCode(), "Robert Pattinson", R.drawable.img_patisson),
            Actor("Tenet".hashCode(), "Elizabeth Debicki", R.drawable.img_debicki),
            Actor("Tenet".hashCode(), "Kenneth Branagh", R.drawable.img_branagh),
            Actor("Tenet".hashCode(), "Dimple Kapadia", R.drawable.img_kapadia),
            Actor("Tenet".hashCode(), "Himesh Patel", R.drawable.img_patel),
            Actor("Black Widow".hashCode(), "Scarlett Johansson", R.drawable.img_johansson),
            Actor("Black Widow".hashCode(), "Florence Pugh", R.drawable.img_pugh),
            Actor("Black Widow".hashCode(), "David Harbour", R.drawable.img_harbour),
            Actor("Black Widow".hashCode(), "O.T. Fagbenle", R.drawable.img_fagbenle),
            Actor("Wonder Woman 1984".hashCode(), "Gal Gadot", R.drawable.img_gadot),
            Actor("Wonder Woman 1984".hashCode(), "Chris Pine", R.drawable.img_pine),
            Actor("Wonder Woman 1984".hashCode(), "Kristen Wiig", R.drawable.img_wiig),
            Actor("Wonder Woman 1984".hashCode(), "Pedro Pascal", R.drawable.img_pascal)
        )
    }

    private fun getActors(movieId: Int) : ArrayList<Actor> = getActors().filter { it.movieId == movieId }.toCollection(arrayListOf())
}