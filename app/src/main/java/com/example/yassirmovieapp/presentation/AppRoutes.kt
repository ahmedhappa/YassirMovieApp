package com.example.yassirmovieapp.presentation

sealed class AppRoutes(val route: String) {
    object MoviesList : AppRoutes("movies_list")
    object MovieDetails : AppRoutes("movie_details")
}
