package com.example.yassirmovieapp.domain.model

data class Movie(
    val id: Int,
    val image: String?,
    val title: String,
    val releaseDate: String?,
    val voteRating: Double
)