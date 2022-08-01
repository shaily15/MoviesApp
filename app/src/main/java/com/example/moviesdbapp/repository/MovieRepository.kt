package com.example.moviesdbapp.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.moviesdbapp.data.MovieDatabase
import com.example.moviesdbapp.data.MovieRemoteMediator
import com.example.moviesdbapp.data.api.MoviesAPI
import com.example.moviesdbapp.paging.MoviePagingSource
import javax.inject.Inject

@ExperimentalPagingApi
class MovieRepository @Inject constructor(private val moviesAPI: MoviesAPI,
                                          private val movieDatabase: MovieDatabase) {

    fun getMovies() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        remoteMediator = MovieRemoteMediator(moviesAPI, movieDatabase),
        pagingSourceFactory = { movieDatabase.movieDao().getMovies() }
    ).liveData
}