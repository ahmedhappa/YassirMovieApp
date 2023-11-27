package com.example.yassirmovieapp.presentation.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.yassirmovieapp.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    val moviesPagingFlow = getMoviesUseCase().cachedIn(viewModelScope)
    private val _state = MutableStateFlow(MoviesState())
    val state = _state.asStateFlow()

    fun onEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.ChangeLoading -> _state.update {
                it.copy(isLoading = event.isLoading)
            }

            is MoviesEvent.SetError -> _state.update {
                it.copy(error = event.error.uiText)
            }
        }
    }
}