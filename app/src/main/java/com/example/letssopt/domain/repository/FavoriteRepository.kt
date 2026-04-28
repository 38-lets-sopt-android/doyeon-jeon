package com.example.letssopt.domain.repository

import com.example.letssopt.domain.model.ContentModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAll(): Flow<Result<List<ContentModel>>>

    suspend fun delete(content: ContentModel): Result<Unit>

    suspend fun save(content: ContentModel): Result<Unit>
}
