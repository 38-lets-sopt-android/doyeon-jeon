package com.example.letssopt.data.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite")
    suspend fun getAll(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun delete(favoriteEntity: FavoriteEntity)
}
