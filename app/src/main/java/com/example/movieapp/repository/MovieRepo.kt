package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList

interface MovieRepo {

    suspend fun getUpcomingMovie(): MovieList
    suspend fun getTopRatedMovie(): MovieList
    suspend fun getPopularMovies(): MovieList

}