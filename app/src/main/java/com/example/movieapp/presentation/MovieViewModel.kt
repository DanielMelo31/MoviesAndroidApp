package com.example.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.movieapp.core.Resource
import com.example.movieapp.data.model.MovieList
import com.example.movieapp.repository.MovieRepo
import kotlinx.coroutines.Dispatchers



class MovieViewModel(private val repo: MovieRepo): ViewModel() {
    fun fetchUpcomingMovie() = liveData<Resource<MovieList>>(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repo.getUpcomingMovieRepo()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class ViewModelFactory(private val repo:MovieRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepo::class.java).newInstance(repo)
    }
}