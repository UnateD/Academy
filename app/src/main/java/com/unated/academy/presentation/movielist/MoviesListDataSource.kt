package com.unated.academy.presentation.movielist

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
            var response: List<Movie> = moviesRepository.getMoviesLocal()
            if(response.isNotEmpty()) {
                callback.onResult(response, null, 2)
                loadingState.postValue(false)
            } else {
                response = moviesRepository.getRemoteMovies(1)
                callback.onResult(response, null, 2)
                loadingState.postValue(false)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        loadingState.postValue(true)
        CoroutineScope(Dispatchers.IO).launch(coroutineException) {
            val response = moviesRepository.getRemoteMovies(params.key)
            callback.onResult(response, params.key + 1)
            loadingState.postValue(false)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}
}