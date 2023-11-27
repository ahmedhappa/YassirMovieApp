package com.example.yassirmovieapp.presentation.movie_details

import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.general.UiText

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val error: UiText? = null
)
