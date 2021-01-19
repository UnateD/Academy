package com.unated.academy.presentation.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.unated.academy.MoviesRepository
import com.unated.academy.data.Movie

class MoviesListDataSourceFactory(var loadingState: MutableLiveData<Boolean>, var moviesRepository: MoviesRepository) : DataSource.Factory<Int, Movie>() {

    var moviesDataSourceLiveData = MutableLiveData<MoviesListDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val dataSource = MoviesListDataSource(loadingState, moviesRepository)
        moviesDataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}