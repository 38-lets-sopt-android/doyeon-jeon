package com.example.letssopt.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.letssopt.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun delete(favoriteEntity: FavoriteEntity)
}