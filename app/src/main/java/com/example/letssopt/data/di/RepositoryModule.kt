package com.example.letssopt.data.di

import com.example.letssopt.data.favorite.FavoriteRepositoryImpl
import com.example.letssopt.domain.repository.FavoriteRepository

object RepositoryModule {
    val favoriteRepository: FavoriteRepository by lazy {
        FavoriteRepositoryImpl(DatabaseModule.favoriteDao)
    }
}
