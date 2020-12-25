package com.unated.academy.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unated.academy.MovieDataSource
import com.unated.academy.data.Movie
import com.unated.academy.data.loadMovies
import kotlinx.coroutines.launch

class MoviesViewModel(val dataSource: MovieDataSource) : ViewModel() {

    private val _movies = MutableLiveData<ArrayList<Movie>>(arrayListOf())
    val movies: LiveData<ArrayList<Movie>> get() = _movies

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _movies.value = dataSource.getMovies()
        }
    }
}