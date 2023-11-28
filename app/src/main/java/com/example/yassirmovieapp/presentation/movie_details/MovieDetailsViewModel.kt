package com.example.yassirmovieapp.presentation.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yassirmovieapp.domain.usecase.GetMovieDetailsUseCase
import com.example.yassirmovieapp.general.Event
import com.example.yassirmovieapp.general.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val movieId = savedStateHandle.get<Int>("movieId")
    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

    init {
        getMovieDetails(movieId)
    }

    private fun getMovieDetails(movieId: Int?) {
        if (movieId == null) {
            Timber.e("movieId is null")
            return
        }

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
                        movie = resource.data,
                        error = null
                    )
                }

                is Resource.Error -> _state.update {
                    it.copy(
                        isLoading = false,
                        error = Event(resource.customAppException.uiText)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}