package com.example.yassirmovieapp.data.mapper

import com.example.yassirmovieapp.data.local.entity.MovieEntity
import com.example.yassirmovieapp.data.remote.dto.MovieResponseDto
import com.example.yassirmovieapp.domain.model.Movie

fun MovieResponseDto.toMovieEntity() = MovieEntity(
    id = id,
    image = posterPath,
    title = title,
    releaseDate = releaseDate,
    voteRating = voteAverage,
    status = status,
    overview = overview
)

fun MovieEntity.toMovie() = Movie(
    id = id,
    image = image,
    title = title,
    releaseDate = releaseDate,
    voteRating = voteRating,
    status = status,
    overview = overview
)