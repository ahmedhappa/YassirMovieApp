package com.example.yassirmovieapp.data.mapper

import com.example.yassirmovieapp.data.local.entity.MovieEntity
import com.example.yassirmovieapp.data.remote.dto.MoviesResponseDto
import com.example.yassirmovieapp.domain.model.Movie

fun MoviesResponseDto.Result.toMovieEntity() = MovieEntity(
    id = id,
    image = posterPath,
    title = title,
    releaseDate = releaseDate,
    voteRating = voteAverage
)

fun MovieEntity.toMovie() = Movie(
    id = id,
    image = image,
    title = title,
    releaseDate = releaseDate,
    voteRating = voteRating
)