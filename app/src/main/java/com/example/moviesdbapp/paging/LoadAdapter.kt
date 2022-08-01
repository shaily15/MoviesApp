package com.example.moviesdbapp.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdbapp.R

class LoadAdapter: LoadStateAdapter<LoadAdapter.LoadViewHolder>() {

    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loader_item, parent, false)
        return LoadViewHolder(view)
    }

    class LoadViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
        }

    }
}