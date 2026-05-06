package com.example.letssopt.data.mapper

import com.example.letssopt.data.local.entity.FavoriteEntity
import com.example.letssopt.domain.model.ContentModel

fun FavoriteEntity.toModel() = ContentModel(
    thumbnailUrl = this.imageUrl,
    title = this.title,
    id = this.id.toString(),
)

fun ContentModel.toEntity() = FavoriteEntity(
    title = this.title ?: "",
    imageUrl = this.thumbnailUrl,
    id = this.id.toLongOrNull() ?: 0,
)
