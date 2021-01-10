package com.unated.academy.presentation.movielist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.unated.academy.MoviesRepository
import com.unated.academy.data.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListDataSource(
    var loadingState: MutableLiveData<Boolean>,
    var moviesRepository: MoviesRepository
) :
    PageKeyedDataSource<Int, Movie>() {

    private val coroutineException = CoroutineExceptionHandler { _, _ ->
        loadingState.postValue(false)
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        loadingState.postValue(true)
        CoroutineScope(Dispatchers.IO).launch(coroutineException) {
            val response = moviesRepository.getMovies(1)
            callback.onResult(response.results, null, 2)
            loadingState.postValue(false)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        loadingState.postValue(true)
        CoroutineScope(Dispatchers.IO).launch(coroutineException) {
            val response = moviesRepository.getMovies(params.key)
            callback.onResult(response.results, params.key + 1)
            loadingState.postValue(false)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}
}