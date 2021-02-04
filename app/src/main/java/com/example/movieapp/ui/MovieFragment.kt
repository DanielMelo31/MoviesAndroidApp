package com.example.movieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.resource.MovieDataSource
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.ViewModelFactory
import com.example.movieapp.repository.MovieRepoImpl
import com.example.movieapp.repository.RetrofitClient
import retrofit2.Retrofit

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> { ViewModelFactory(MovieRepoImpl(
        MovieDataSource(RetrofitClient.webService)
    )) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "Upcomig:${result.data.first} \n \n Popular:${result.data.second} \n \n TopRated:${result.data.third}")
                }
                is Resource.Failure -> {
                    Log.d("Failure", "${result.exception}")
                }
            }
        })
    }
}