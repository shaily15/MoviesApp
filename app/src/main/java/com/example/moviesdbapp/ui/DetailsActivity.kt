package com.example.moviesdbapp.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviesdbapp.R
import com.example.moviesdbapp.utils.Constants

class DetailsActivity : AppCompatActivity() {

    var imageUrl: String? = null
    var name: String? = null
    var overview: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (intent.hasExtra("image_url") && intent.hasExtra("name") && intent.hasExtra("overview")) {
            imageUrl = intent.getStringExtra("image_url")
            name = intent.getStringExtra("name")
            overview = intent.getStringExtra("overview")
        }

        val tvName: TextView = findViewById(R.id.tv_movie)
        val tvOverview: TextView = findViewById(R.id.tv_overview)
        val imgUrl: ImageView = findViewById(R.id.iv_movie_icon)

        tvName.text = name
        tvOverview.text = overview
        Glide.with(this).load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imgUrl)
    }
}