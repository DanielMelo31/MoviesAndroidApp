package com.example.movieapp.data.resource

import com.example.movieapp.data.model.MovieList
import com.example.movieapp.repository.WebService
import com.example.movieapp.utils.AppConstants

class MovieDataSourceRemote(private val webService: WebService) {

    suspend fun getUpcomingMovie(): MovieList = webService.getUpcomingMovie(AppConstants.API_KEY)

    suspend fun getTopRatedMovie(): MovieList = webService.getTopRatedMovie(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

}