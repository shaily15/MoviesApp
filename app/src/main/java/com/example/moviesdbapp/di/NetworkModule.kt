package com.example.moviesdbapp.di

import com.example.moviesdbapp.data.api.MoviesAPI
import com.example.moviesdbapp.data.api.OAuthInterceptor
import com.example.moviesdbapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(OAuthInterceptor(BEARER, TOKEN))
        .build()

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

    @Singleton
    @Provides
    fun getMoviesApi(retrofit: Retrofit): MoviesAPI {
        return retrofit.create(MoviesAPI::class.java)
    }

    companion object {
        private const val BEARER = "Bearer"
        private const val TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzN2IyMDJkNjkzNzY0NjhlMTBkNDAyNTE1MWMxNzUyZiIsInN1YiI6IjYyZTc4OThhZmRmOGI3MDA1OWJlMjY1MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7Zt1zpp7sS8OvWl-yFrWgkZg-6Yn7kWcuLuZT8F2V6I"
    }
}