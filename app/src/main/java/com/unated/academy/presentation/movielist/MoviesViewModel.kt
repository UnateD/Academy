package com.unated.academy.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.unated.academy.MoviesRepository
import com.unated.academy.data.Configuration
import com.unated.academy.data.Movie
import kotlinx.coroutines.launch

class MoviesViewModel() : ViewModel() {

    private var repository = MoviesRepository()

    private var _loading = MutableLiveData(false)
    var loading: LiveData<Boolean> = _loading

    lateinit var movies: LiveData<PagedList<Movie>>

    init {
        getMovies()
    }

    private fun getMovies() {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(false)
            .build()
        movies = LivePagedListBuilder(MoviesListDataSourceFactory(_loading, repository), config).build()
    }
}