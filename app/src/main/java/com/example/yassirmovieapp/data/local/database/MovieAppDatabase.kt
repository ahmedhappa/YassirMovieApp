package com.example.yassirmovieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yassirmovieapp.data.local.dao.MovieDao
import com.example.yassirmovieapp.data.local.dao.RemoteKeyDao
import com.example.yassirmovieapp.data.local.entity.MovieEntity
import com.example.yassirmovieapp.data.local.entity.RemoteKeyEntity

@Database(
    entities = [MovieEntity::class, RemoteKeyEntity::class],
    version = 1
)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val remoteKeyDao: RemoteKeyDao
}