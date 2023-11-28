package com.example.yassirmovieapp.presentation.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.yassirmovieapp.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    val moviesPagingFlow = getMoviesUseCase().cachedIn(viewModelScope)
}