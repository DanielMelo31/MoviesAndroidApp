package com.example.movieapp.data.local

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieEntity
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.data.model.toMovieList

class LocalMovieDataSource(private val moviesDao: MoviesDao) {
    suspend fun getUpcomingMovies(): MovieList{
        return moviesDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList{
        return moviesDao.getAllMovies().filter { it.movie_type == "top_rated" }.toMovieList()
    }

    suspend fun getPopularMovies(): MovieList{
        return moviesDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity) {
        moviesDao.insertMovie(movie)
    }
}