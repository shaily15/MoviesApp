package com.example.moviesdbapp.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.example.moviesdbapp.data.models.MovieList
import com.example.moviesdbapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MovieViewModel @Inject constructor(val repository: MovieRepository): ViewModel() {

    val moviesList = repository.getMovies().cachedIn(viewModelScope)
}