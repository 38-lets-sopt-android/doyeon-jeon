package com.example.letssopt.data.di

import com.example.letssopt.data.auth.AuthPreferences
import com.example.letssopt.data.auth.AuthRepositoryImpl
import com.example.letssopt.data.favorite.FavoriteRepositoryImpl
import com.example.letssopt.domain.repository.AuthRepository
import com.example.letssopt.domain.repository.FavoriteRepository

object RepositoryModule {
    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(AuthPreferences)
    }

    val favoriteRepository: FavoriteRepository by lazy {
        FavoriteRepositoryImpl(DatabaseModule.favoriteDao)
    }
}
