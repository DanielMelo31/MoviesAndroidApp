package com.example.movieapp.ui.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.core.BaseConcatHolder
import com.example.movieapp.databinding.PopularMoviesRowBinding
import com.example.movieapp.ui.adapters.MovieAdapter

class PopularConcatAdapter(private val movieAdapter: MovieAdapter) : RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = PopularMoviesRowBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when (holder){
            is ConcatViewHolder -> holder.bind(movieAdapter)
        }
    }

    private inner class ConcatViewHolder( val itemBinding: PopularMoviesRowBinding) : BaseConcatHolder<MovieAdapter>(itemBinding.root) {
        override fun bind(adapter: MovieAdapter) {
            itemBinding.rvPopularMoviesRow.adapter = adapter
        }
    }
}