package com.example.moviesdbapp.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.example.moviesdbapp.data.models.Results

@Dao
interface MovieDao {

    @Query("Select distinct * from Movie")
    fun getMovies(): PagingSource<Int, Results>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movies: List<Results>)

    @Query("DELETE FROM Movie")
    suspend fun deleteMovies()
}