package com.unated.academy

import com.unated.academy.data.AppDatabase
import com.unated.academy.data.Genre
import com.unated.academy.data.Movie
import com.unated.academy.data.MovieDetails

class MoviesRepository(var appDb: AppDatabase) {

    suspend fun getMoviesLocal(): List<Movie> {
        return appDb.dao.getMovies()
    }

    suspend fun getRemoteForWorker() : Movie {
        getRemoteMovies(1)
        return appDb.dao.getMax()
    }

    suspend fun getRemoteMovies(page: Int): List<Movie> {
        val genresResponse = getGenres()
        val response = Retrofit.appApi.getMovies(page)
        response.results.forEach { movie ->
            movie.displayGenres = arrayListOf()
            movie.genres.forEach { genre ->
                genresResponse.find { it.id == genre }?.let { movie.displayGenres.add(it) }
            }
            val movieResponse = getDetails(movie.id)
            movie.runtime = movieResponse.runtime
        }
        if(response.page == 1) {
            appDb.dao.setMovies(response.results)
        }

        return response.results
    }

    suspend fun getMovieById(movieId: Int): MovieDetails {
        var details = appDb.dao.getMovieDetails(movieId)
        return if(details == null) {
            val castResponse = getActors(movieId).cast
            details = Retrofit.appApi.getMovieDetails(movieId)
            details.actors = arrayListOf()
            details.actors.addAll(castResponse)

            appDb.dao.setMovieDetails(details)
            details
        } else {
            details
        }
    }

    private suspend fun getDetails(movieId: Int) = Retrofit.appApi.getMovieDetails(movieId)

    private suspend fun getActors(movieId: Int) = Retrofit.appApi.getActors(movieId)

    private suspend fun getGenres(): List<Genre> {
        var genres: List<Genre> = appDb.dao.getGenres()
        return if (genres.isEmpty()) {
            genres = Retrofit.appApi.getGenres().genres
            appDb.dao.setGenres(genres)
            genres
        } else {
            genres
        }
    }

    suspend fun getConfiguration() = Retrofit.appApi.getConfiguration()
}