package com.example.moviesdbapp.data.api

import com.example.moviesdbapp.data.models.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("movie/popular")
    suspend fun getMovieList(@Query("page") page: Int) : MovieList

}

