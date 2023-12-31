package com.example.yassirmovieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val databaseKey: String,
    val id: Int,
    val image: String?,
    val backgroundImage:String?,
    val title: String,
    val releaseDate: String?,
    val voteRating: Double,
    val status: String?,
    val overview: String?
)
