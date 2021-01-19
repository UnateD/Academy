package com.unated.academy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unated.academy.activity.MainViewModel
import com.unated.academy.presentation.moviedetails.MovieDetailsViewModel
import com.unated.academy.presentation.movielist.MoviesViewModel

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MovieDetailsViewModel::class.java -> MovieDetailsViewModel()
            MoviesViewModel::class.java -> MoviesViewModel()
            MainViewModel::configuration -> MainViewModel()
            else -> throw IllegalStateException("ViewModel class $modelClass not found")
        } as T
    }
}