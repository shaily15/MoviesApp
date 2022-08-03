package com.example.moviesdbapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesdbapp.data.db.MovieDao
import com.example.moviesdbapp.data.db.RemoteKeysDao
import com.example.moviesdbapp.data.models.MovieRemoteKeys
import com.example.moviesdbapp.data.models.Results

@Database(entities = [Results::class, MovieRemoteKeys::class], version = 5)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}