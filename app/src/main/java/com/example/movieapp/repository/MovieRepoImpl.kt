package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.resource.MovieDataSource

class MovieRepoImpl(private val dataSource: MovieDataSource): MovieRepo{
    override suspend fun getUpcomingMovieRepo(): MovieList = dataSource.getUpcomingMovie()

    override suspend fun getTopRatedMovieRepo(): MovieList = dataSource.getTopRatedMovie()

    override suspend fun getPopularMoviesRepo(): MovieList = dataSource.getPopularMovies()
}