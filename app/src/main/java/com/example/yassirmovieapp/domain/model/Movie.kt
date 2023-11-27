package com.example.yassirmovieapp.domain.model

data class Movie(
    val id: Int,
    val image: String?,
    val title: String,
    val releaseDate: String?,
    val voteRating: Double,
    val status: String?,
    val overview: String?,
){
    val fullImageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$image"
}
