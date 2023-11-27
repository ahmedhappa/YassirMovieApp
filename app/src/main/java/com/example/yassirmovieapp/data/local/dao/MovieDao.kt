package com.example.yassirmovieapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.yassirmovieapp.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM MovieEntity")
    fun pagingSource(): PagingSource<Int, MovieEntity>

    @Query("DELETE FROM MovieEntity")
    suspend fun clearAll()
}