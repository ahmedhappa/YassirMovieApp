package com.example.yassirmovieapp.presentation.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yassirmovieapp.domain.usecase.GetMovieDetailsUseCase
import com.example.yassirmovieapp.general.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

    fun onEvent(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.GetMovieDetails -> {
                getMovie(event.movieId)
            }
        }
    }

    private fun getMovie(movieId: Int) {
        getMovieDetailsUseCase(movieId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> _state.update {
                    it.copy(
                        isLoading = true,
                        movie = resource.data
                    )
                }

                is Resource.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        movie = resource.data
                    )
                }

                is Resource.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        error = resource.customAppException.uiText
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}