package com.unated.academy.presentation.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unated.academy.MovieDataSource
import com.unated.academy.data.Movie
import com.unated.academy.data.loadMovies
import kotlinx.coroutines.launch

class MovieDetailsViewModel(val dataSource: MovieDataSource) : ViewModel() {

    private var _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun getMovie(id: Int) {
        viewModelScope.launch {
            _movie.postValue(dataSource.getMovieById(id))
        }
    }

}