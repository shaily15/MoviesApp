package com.example.moviesdbapp.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesdbapp.data.models.MovieRemoteKeys
import com.example.moviesdbapp.data.models.Results

@Dao
interface RemoteKeysDao {

    @Query("Select * from RemoteKeys where id =:id")
    suspend fun getRemoteKeys(id: Int): MovieRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<MovieRemoteKeys>)

    @Query("DELETE FROM RemoteKeys")
    suspend fun deleteAllRemoteKeys()
}