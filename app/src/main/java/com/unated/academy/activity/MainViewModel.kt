package com.unated.academy.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unated.academy.MoviesRepository
import com.unated.academy.data.Configuration
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var _configuration = MutableLiveData<Configuration>()
    var configuration: LiveData<Configuration> = _configuration

    init {
        getConfiguration()
    }

    private fun getConfiguration() {
        viewModelScope.launch {
            _configuration.value = repository.getConfiguration()
        }
    }
}