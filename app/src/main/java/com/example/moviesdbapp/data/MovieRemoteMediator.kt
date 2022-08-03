package com.example.moviesdbapp.data

import android.util.Log
import androidx.paging.*
import androidx.room.withTransaction
import com.example.moviesdbapp.data.api.MoviesAPI
import com.example.moviesdbapp.data.models.MovieRemoteKeys
import com.example.moviesdbapp.data.models.Results
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class MovieRemoteMediator(
    val moviesAPI: MoviesAPI,
    val movieDatabase: MovieDatabase
) : RemoteMediator<Int, Results>() {

    val movieDao = movieDatabase.movieDao()
    val movieRemoteKeysDao = movieDatabase.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Results>
    ): MediatorResult {

        return try {
            val currentPage = 1
//                when (loadType) {
//                LoadType.REFRESH ->
//                {
//                    val remoteKeys = getRemoteKeyForClosestIndex(state)
//                    remoteKeys?.nextPage?.minus(1) ?: 1
//                }
//                LoadType.PREPEND -> {
//                    val remoteKeys = getRemoteKeysForFirstItem(state)
//                    val prevPage = remoteKeys?.prevPage ?:
//                    MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                    prevPage
//                }
//                LoadType.APPEND -> {
//                    val remoteKeys = getRemoteKeysForLastItem(state)
//                    val nextPage = remoteKeys?.nextPage ?:
//                    MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
//                    nextPage
//                }

//            }

            val response = moviesAPI.getMovieList(currentPage as Int)
            Log.d("response 123 : ", "load: " + response.toString())
            val endOfPaginationReached = response.totalPages == currentPage

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            movieDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    movieDao.deleteMovies()
                    movieRemoteKeysDao.deleteAllRemoteKeys()
                }

                movieDao.addMovie(response.results)
                val remoteKeys = response.results.map { movie ->
                    MovieRemoteKeys(
                        id = movie.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                movieRemoteKeysDao.addAllRemoteKeys(remoteKeys)
            }
            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, Results>
    ): MovieRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { movie ->
            movieRemoteKeysDao.getRemoteKeys(id = movie.id)
        }
    }

    private suspend fun getRemoteKeyForClosestIndex(
        state: PagingState<Int, Results>
    ): MovieRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                movieRemoteKeysDao.getRemoteKeys(id)
            }
        }
    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, Results>
    ): MovieRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { movie ->
            movieRemoteKeysDao.getRemoteKeys(id = movie.id)
        }
    }
}