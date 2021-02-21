package com.example.movieapp.repository

import com.example.movieapp.core.InternetCheck
import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.resource.MovieDataSourceRemote

class MovieRepoImpl(
        private val dataSourceRemote: MovieDataSourceRemote,
        private val dataSourceLocal: LocalMovieDataSource
) : MovieRepo {
    override suspend fun getUpcomingMovieRepo(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getUpcomingMovie().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovieRepo(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getTopRatedMovie().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("top_rated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else
            dataSourceLocal.getTopRatedMovies()
    }

    override suspend fun getPopularMoviesRepo(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            return dataSourceLocal.getPopularMovies()
        } else
            dataSourceLocal.getPopularMovies()
    }
}