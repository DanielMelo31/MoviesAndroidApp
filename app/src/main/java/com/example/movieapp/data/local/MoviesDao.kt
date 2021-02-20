package com.example.movieapp.data.local

import androidx.room.*
import com.example.movieapp.data.model.MovieEntity


@Dao
interface MoviesDao {

    @Query("SELECT * FROM movieentity")
    suspend fun getAllMovies() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)
}