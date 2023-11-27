package com.example.yassirmovieapp.domain.repository

import androidx.paging.PagingData
import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.general.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>

    fun getMovieDetails(movieId: Int): Flow<Resource<Movie?>>
}