package com.example.yassirmovieapp.domain.usecase

import com.example.yassirmovieapp.domain.model.Movie
import com.example.yassirmovieapp.domain.repository.MovieRepository
import com.example.yassirmovieapp.general.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int?) : Flow<Resource<Movie?>> {
        return movieRepository.getMovieDetails(movieId = movieId ?: -1)
    }
}