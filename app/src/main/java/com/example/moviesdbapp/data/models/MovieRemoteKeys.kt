package com.example.moviesdbapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeys")
data class MovieRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
