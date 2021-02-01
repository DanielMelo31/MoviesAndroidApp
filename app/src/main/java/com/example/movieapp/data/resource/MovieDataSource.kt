package com.example.movieapp.data.resource

import com.example.movieapp.data.model.MovieList

class MovieDataSource {

    fun getUpcomingMovie(): MovieList {
        return MovieList()
    }

    fun getTopRatedMovie(): MovieList {
        return MovieList()
    }

    fun getPopularMovies(): MovieList {
        return MovieList()
    }
}