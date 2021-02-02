package com.unated.academy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unated.academy.activity.MainViewModel
import com.unated.academy.presentation.moviedetails.MovieDetailsViewModel
import com.unated.academy.presentation.movielist.MoviesViewModel

class ViewModelFactory(private val repository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MovieDetailsViewModel::class.java -> MovieDetailsViewModel(repository)
            MoviesViewModel::class.java -> MoviesViewModel(repository)
            MainViewModel::class.java -> MainViewModel(repository)
            else -> throw IllegalStateException("ViewModel class $modelClass not found")
        } as T
    }
}