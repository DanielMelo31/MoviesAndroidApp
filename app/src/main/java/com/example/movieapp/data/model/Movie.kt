package com.example.movieapp.data.model

import javax.sql.StatementEvent

data class Movie(
        val id: Int = 1,
        val adult: Boolean = false,
        val poster_path: String = "",
        val overview: String = "",
        val release_date: String = "",
        val genre_id: List<Int> = listOf(),
        val original_title: String = "",
        val original_language: String = "",
        val title: String = "",
        val backdrop_path: String = "",
        val popularity: Double = 1.0,
        val video: Boolean = false,
        val vote_average: Double = 1.0,
        val vote_count: Double = 1.0
)

data class MovieList(val results: List<Movie> = listOf())