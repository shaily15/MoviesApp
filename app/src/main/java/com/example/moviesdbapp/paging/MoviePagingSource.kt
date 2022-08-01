package com.example.moviesdbapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesdbapp.data.api.MoviesAPI
import com.example.moviesdbapp.data.models.Results

class MoviePagingSource(val moviesAPI: MoviesAPI): PagingSource<Int, Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        TODO("Not yet implemented")
    }

}