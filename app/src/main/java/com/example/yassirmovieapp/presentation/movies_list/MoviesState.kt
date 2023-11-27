package com.example.yassirmovieapp.presentation.movies_list

import com.example.yassirmovieapp.general.UiText

data class MoviesState(
    val isLoading: Boolean = false,
    val error: UiText? = null
)
