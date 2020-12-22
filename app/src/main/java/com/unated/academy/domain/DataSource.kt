package com.unated.academy.domain

import android.content.Context
import com.unated.academy.data.Movie
import com.unated.academy.data.loadMovies

class DataSource(var context: Context) {

    suspend fun getMovies(): ArrayList<Movie> {
        return loadMovies(context = context)
    }

    suspend fun getMovieById(id: Int): Movie? = getMovies().find { it.id == id }
}