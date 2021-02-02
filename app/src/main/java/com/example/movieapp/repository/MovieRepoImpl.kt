package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.resource.MovieDataSource

class MovieRepoImpl(private val dataSource: MovieDataSource) : MovieRepo{

    override suspend fun getUpcomingMovie(): MovieList = dataSource.getUpcomingMovie()

    override suspend fun getTopRatedMovie(): MovieList = dataSource.getTopRatedMovie()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()
}