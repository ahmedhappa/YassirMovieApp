package com.example.yassirmovieapp.data.remote.api

import com.example.yassirmovieapp.data.remote.dto.MovieResponseDto
import com.example.yassirmovieapp.data.remote.dto.MoviesListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAppApi {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int
    ): MoviesListResponseDto

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int
    ): MovieResponseDto
}