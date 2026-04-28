package com.example.letssopt.data.favorite

import com.example.letssopt.domain.model.ContentModel
import com.example.letssopt.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao,
) : FavoriteRepository {
    override fun getAll(): Flow<Result<List<ContentModel>>> = favoriteDao.getAll().map { entities ->
        Result.success(
            entities.map { entity -> entity.toModel() }
        )
    }.catch { emit(Result.failure(it)) }

    override suspend fun delete(content: ContentModel): Result<Unit> =
        runCatching { favoriteDao.delete(content.toEntity()) }

    override suspend fun save(content: ContentModel): Result<Unit> =
        runCatching { favoriteDao.insert(content.toEntity()) }
}
