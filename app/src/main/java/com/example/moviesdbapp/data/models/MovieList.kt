package com.example.moviesdbapp.data.models


data class MovieList(
                     val page: Int = 0,
                     val totalPages: Int = 0,
                     val results: List<Results>,
                     val totalResults: Int = 0)