package com.unated.academy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unated.academy.presentation.moviedetails.MovieDetailsViewModel
import com.unated.academy.presentation.movielist.MoviesViewModel

class ViewModelFactory(private val dataSource: MovieDataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MovieDetailsViewModel::class.java -> MovieDetailsViewModel(dataSource)
            MoviesViewModel::class.java -> MoviesViewModel(dataSource)
            else -> throw IllegalStateException("ViewModel class $modelClass not found")
        } as T
    }
}