package com.example.moviesdbapp.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, "movieDb").fallbackToDestructiveMigration().
                build()
    }
}