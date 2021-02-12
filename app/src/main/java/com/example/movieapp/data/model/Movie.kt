package com.example.movieapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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
        val vote_count: Int = 1
)

data class MovieList(val results: List<Movie> = listOf())

//Room

@Entity
data class MovieEntity(
        @PrimaryKey()
        val id: Int = 1,
        @ColumnInfo(name = "adult")
        val adult: Boolean = false,
        @ColumnInfo(name = "poster_path" )
        val poster_path: String = "",
        @ColumnInfo(name = "overview")
        val overview: String = "",
        @ColumnInfo(name = "release_date" )
        val release_date: String = "",
        @ColumnInfo(name = "original_title")
        val original_title: String = "",
        @ColumnInfo(name = "original_language")
        val original_language: String = "",
        @ColumnInfo(name = "title")
        val title: String = "",
        @ColumnInfo(name = "backdrop_path")
        val backdrop_path: String = "",
        @ColumnInfo(name = "popularity")
        val popularity: Double = 1.0,
        @ColumnInfo(name = "video")
        val video: Boolean = false,
        @ColumnInfo(name = "vote_average")
        val vote_average: Double = 1.0,
        @ColumnInfo(name = "vote_average")
        val vote_count: Int = 1
)