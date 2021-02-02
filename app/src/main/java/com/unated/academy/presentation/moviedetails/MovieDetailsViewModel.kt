package com.unated.academy.presentation.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unated.academy.MoviesRepository
import com.unated.academy.data.Configuration
import com.unated.academy.data.Movie
import com.unated.academy.data.MovieDetails
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var _movie = MutableLiveData<MovieDetails>()
    val movie: LiveData<MovieDetails> get() = _movie

    fun getMovie(id: Int) {
        viewModelScope.launch {
            _movie.postValue(repository.getMovieById(id))
        }
    }

}