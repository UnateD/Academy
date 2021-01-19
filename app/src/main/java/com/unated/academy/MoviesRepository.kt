package com.unated.academy

import com.unated.academy.data.MovieDetails
import com.unated.academy.data.MoviesResponse

class MoviesRepository {

    suspend fun getMovies(page: Int): MoviesResponse {
        val genresResponse = getGenres().genres
        val response = Retrofit.appApi.getMovies(page)
        response.results.forEach { movie ->
            movie.displayGenres = arrayListOf()
            movie.genres.forEach { genre ->
                genresResponse.find { it.id == genre }?.let { movie.displayGenres.add(it) }
            }
            val movieResponse = getDetails(movie.id)
            movie.runtime = movieResponse.runtime
        }
        return response
    }

    suspend fun getMovieById(movieId: Int): MovieDetails {
        val castResponse = getActors(movieId).cast
        val movie = Retrofit.appApi.getMovieDetails(movieId)
        movie.actors = arrayListOf()
        movie.actors.addAll(castResponse)
        return movie
    }

    suspend fun getDetails(movieId: Int) = Retrofit.appApi.getMovieDetails(movieId)

    suspend fun getActors(movieId: Int) = Retrofit.appApi.getActors(movieId)

    suspend fun getGenres() = Retrofit.appApi.getGenres()

    suspend fun getConfiguration() = Retrofit.appApi.getConfiguration()
}