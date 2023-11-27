package com.example.yassirmovieapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MoviesListResponseDto(
    val page: Int,
    val results: List<MovieResponseDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)