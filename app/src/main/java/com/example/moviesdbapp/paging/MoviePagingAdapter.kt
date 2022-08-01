package com.example.moviesdbapp.paging

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesdbapp.R
import com.example.moviesdbapp.data.models.Results
import javax.inject.Inject

class MoviePagingAdapter @Inject constructor(): PagingDataAdapter<Results, MoviePagingAdapter.MovieViewHolder>(COMPARATOR) {

    var onItemClick: ((Results) -> Unit)? = null
    var movieItem: List<Results> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            holder.tvMovieName.text = item.name
            holder.tvMovieName.setOnClickListener {(holder.tvMovieName.setTextColor(Color.GREEN))} // click event
            Glide.with(holder.itemView.context).load(item.posterPath)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivMovieIcon)
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMovieName: TextView = itemView.findViewById<TextView>(R.id.tv_movie)
        val ivMovieIcon: ImageView = itemView.findViewById<ImageView>(R.id.iv_movie_icon)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem == newItem
            }
        }
    }
}