package com.example.movieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.movieapp.R
import com.example.movieapp.core.Resource
import com.example.movieapp.data.local.AppDatabase
import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.local.MoviesDao
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.resource.MovieDataSourceRemote
import com.example.movieapp.databinding.FragmentMovieBinding
import com.example.movieapp.presentation.MovieViewModel
import com.example.movieapp.presentation.ViewModelFactory
import com.example.movieapp.repository.MovieRepoImpl
import com.example.movieapp.repository.RetrofitClient
import com.example.movieapp.ui.adapters.MovieAdapter
import com.example.movieapp.ui.adapters.concat.PopularConcatAdapter
import com.example.movieapp.ui.adapters.concat.TopRatedConcatAdapter
import com.example.movieapp.ui.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        ViewModelFactory(
            MovieRepoImpl(
                MovieDataSourceRemote(RetrofitClient.webService),
                LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }
    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE

                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                com.example.movieapp.ui.adapters.MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                com.example.movieapp.ui.adapters.MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                com.example.movieapp.ui.adapters.MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }

                    binding.recyclerView.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Failure", "${result.exception}")
                }
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}