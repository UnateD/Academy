package com.unated.academy

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import com.unated.academy.data.*
import java.util.concurrent.TimeUnit

class MoviesRepository(context: Context) {

    private val appDb = AppDatabase.create(context)
    private val notifications: INotifications = Notifications(context)

    init {
        notifications.initialize()
    }

    suspend fun getMoviesLocal(): List<Movie> {
        return appDb.dao.getMovies()
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
            notifications.showNotification(appDb.dao.getMax())
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