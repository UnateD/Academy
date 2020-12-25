package com.unated.academy

import android.content.Context
import com.unated.academy.data.getMovie
import com.unated.academy.data.loadMovies

class MovieDataSource(private val context: Context) {

    suspend fun getMovies() = loadMovies(context)

    suspend fun getMovieById(id: Int) = getMovie(context, id)

}