package com.example.letssopt.data.favorite

import com.example.letssopt.domain.model.ContentModel
import okhttp3.internal.toLongOrDefault

fun FavoriteEntity.toModel() = ContentModel(
    thumbnailUrl = this.imageUrl,
    title = this.title,
    id = this.id.toString(),
)

fun ContentModel.toEntity() = FavoriteEntity(
    title = this.title ?: "",
    imageUrl = this.thumbnailUrl,
    id = this.id.toLongOrDefault(0),
)
