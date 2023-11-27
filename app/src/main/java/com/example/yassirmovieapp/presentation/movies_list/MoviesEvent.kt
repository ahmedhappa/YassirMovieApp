package com.example.yassirmovieapp.presentation.movies_list

import com.example.yassirmovieapp.general.CustomAppException

sealed interface MoviesEvent{
    data class ChangeLoading(val isLoading: Boolean) : MoviesEvent
    data class SetError(val error: CustomAppException) : MoviesEvent
}