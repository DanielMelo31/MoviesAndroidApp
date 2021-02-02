package com.example.movieapp.repository

import com.example.movieapp.data.model.MovieList
import com.example.movieapp.utils.AppConstants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("upcoming")
    suspend fun getUpcomingMovie(@Query("api_key") apiKey: String): MovieList

    @GET("top_rated")
    suspend fun getTopRatedMovie(@Query("api_key") apiKey: String): MovieList

    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

}

object RetrofitClient {
    val webService by lazy {
        Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(WebService::class.java)
    }
}