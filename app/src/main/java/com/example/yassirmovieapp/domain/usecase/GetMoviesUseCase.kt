package com.example.yassirmovieapp.domain.usecase

import androidx.paging.PagingData
import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return movieRepository.getMovies()
    }
}