package com.example.movieapp.repository

import com.example.movieapp.data.local.LocalMovieDataSource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieEntity
import com.example.movieapp.data.resource.MovieDataSourceRemote

class MovieRepoImpl(
    private val dataSourceRemote: MovieDataSourceRemote,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepo {
    override suspend fun getUpcomingMovieRepo(): MovieList {
        dataSourceRemote.getUpcomingMovie().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
        }
        return dataSourceLocal.getUpcomingMovies()
    }

    override suspend fun getTopRatedMovieRepo(): MovieList {
        dataSourceRemote.getTopRatedMovie().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("top_rated"))
        }
        return dataSourceLocal.getTopRatedMovies()
    }

    override suspend fun getPopularMoviesRepo(): MovieList {
        dataSourceRemote.getPopularMovies().results.forEach { movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
        }
        return dataSourceLocal.getPopularMovies()
    }
}