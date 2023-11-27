package com.example.yassirmovieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.yassirmovieapp.data.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {

    @Upsert
    suspend fun upsertRemoteKey(remoteKey: RemoteKeyEntity)

    @Query("SELECT * FROM RemoteKeyEntity WHERE label = :label")
    suspend fun getRemoteKeyByLabel(label: String): RemoteKeyEntity?

    @Query("DELETE FROM RemoteKeyEntity WHERE label = :label")
    suspend fun deleteRemoteKeyByLabel(label: String)
}