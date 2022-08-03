package com.example.moviesdbapp.paging

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesdbapp.R
import com.example.moviesdbapp.data.models.Results
import com.example.moviesdbapp.ui.DetailsActivity
import com.example.moviesdbapp.utils.Constants.IMAGE_PATH
import javax.inject.Inject


class MoviePagingAdapter @Inject constructor(): PagingDataAdapter<Results, MoviePagingAdapter.MovieViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null) {
            val imageUrl = "$IMAGE_PATH${item.poster_path.toString()}"
            holder.tvMovieName.text = item.title
           // click event
            holder.llParentLayout.setOnClickListener(View.OnClickListener {
                val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
                intent.putExtra("image_url", imageUrl)
                intent.putExtra("name", item.title)
                intent.putExtra("overview", item.overview)
                holder.itemView.context.startActivity(intent)
            })
            Glide.with(holder.itemView.context).load("$IMAGE_PATH${item.poster_path.toString()}")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.ivMovieIcon)
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMovieName: TextView = itemView.findViewById<TextView>(R.id.tv_movie)
        val ivMovieIcon: ImageView = itemView.findViewById<ImageView>(R.id.iv_movie_icon)
        val llParentLayout: LinearLayout = itemView.findViewById<LinearLayout>(R.id.ll_parent)
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