package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList

interface MovieRepo {

    suspend fun getUpcomingMovieRepo(): MovieList
    suspend fun getTopRatedMovieRepo(): MovieList
    suspend fun getPopularMoviesRepo(): MovieList

}