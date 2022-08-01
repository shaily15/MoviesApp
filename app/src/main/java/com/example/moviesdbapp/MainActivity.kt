package com.example.moviesdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdbapp.paging.LoadAdapter
import com.example.moviesdbapp.paging.MoviePagingAdapter
import com.example.moviesdbapp.viemodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MoviePagingAdapter
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_movie_list)
        adapter = MoviePagingAdapter()
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadAdapter(),
            footer = LoadAdapter()
        )

        viewModel.moviesList.observe(this) { it ->
            adapter.submitData(lifecycle, it)
        }

        adapter.onItemClick = { movie ->
            Toast.makeText(applicationContext, "${movie.name}", Toast.LENGTH_SHORT).show() }
    }
}