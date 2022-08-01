package com.example.moviesdbapp.data.models

import com.example.moviesdbapp.data.models.Results

data class MovieList(val id: Int = 0,
                     val page: Int = 0,
                     val totalPages: Int = 0,
                     val results: List<Results>,
                     val totalResults: Int = 0)