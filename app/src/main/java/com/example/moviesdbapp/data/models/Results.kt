package com.example.moviesdbapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class Results(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val title: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val overview: String? = null,
    val poster_path: String? = null
)