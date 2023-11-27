package com.example.yassirmovieapp.presentation.movie_details

sealed interface MovieDetailsEvent {
    data class GetMovieDetails(val movieId: Int) : MovieDetailsEvent
}