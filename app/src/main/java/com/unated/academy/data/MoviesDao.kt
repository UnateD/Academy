package com.unated.academy.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<Movie>

    @Query("SELECT * FROM details WHERE id == :movieId")
    suspend fun getMovieDetails(movieId: Int): MovieDetails?

    @Query("SELECT * FROM genres")
    suspend fun getGenres(): List<Genre>

    @Query("SELECT * FROM movies ORDER BY ratings DESC LIMIT 1")
    suspend fun getMax(): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setMovies(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setGenres(genres: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setMovieDetails(details: MovieDetails)
}