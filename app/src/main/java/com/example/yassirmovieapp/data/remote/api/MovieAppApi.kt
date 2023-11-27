package com.example.yassirmovieapp.data.remote.api

import com.example.yassirmovieapp.data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAppApi {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MoviesResponseDto
}