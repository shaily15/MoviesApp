package com.example.moviesdbapp.data.models

data class Results(val itemCount: Int = 0,
                   val listType: String? = null,
                   val name: String? = null,
                   val description: String? = null,
                   val favoriteCount: Int = 0,
                   val id: Int = 0,
                   val iso: String? = null,
                    val posterPath: String? = null)