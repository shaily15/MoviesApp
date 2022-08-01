package com.example.moviesdbapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class Results(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val itemCount: Int = 0,
    val listType: String? = null,
    val name: String? = null,
    val description: String? = null,
    val favoriteCount: Int = 0,
    val iso: String? = null,
    val posterPath: String? = null
)