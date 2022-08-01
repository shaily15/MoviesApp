package com.example.moviesdbapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.moviesdbapp.data.api.MoviesAPI
import com.example.moviesdbapp.paging.MoviePagingSource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val moviesAPI: MoviesAPI) {

    fun getMovies() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { MoviePagingSource(moviesAPI) }
    ).liveData
}