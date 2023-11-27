package com.example.yassirmovieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeyEntity(
    @PrimaryKey
    val label: String,
    val nextKey: Int
)
