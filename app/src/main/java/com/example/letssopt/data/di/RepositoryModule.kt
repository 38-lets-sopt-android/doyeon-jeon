package com.example.letssopt.data.di

import com.example.letssopt.data.di.NetworkModule.apiResponseHandler
import com.example.letssopt.data.di.ServiceModule.authService
import com.example.letssopt.data.di.ServiceModule.userService
import com.example.letssopt.data.local.datasource.AuthLocalDataSource
import com.example.letssopt.data.repository.AuthRepositoryImpl
import com.example.letssopt.data.repository.FavoriteRepositoryImpl
import com.example.letssopt.data.repository.UserRepositoryImpl
import com.example.letssopt.domain.repository.AuthRepository
import com.example.letssopt.domain.repository.FavoriteRepository
import com.example.letssopt.domain.repository.UserRepository

object RepositoryModule {
    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(
            apiResponseHandler = apiResponseHandler,
            authLocalDataSource = AuthLocalDataSource,
            authService = authService,
        )
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            apiResponseHandler = apiResponseHandler,
            authLocalDataSource = AuthLocalDataSource,
            userService = userService,
        )
    }

    val favoriteRepository: FavoriteRepository by lazy {
        FavoriteRepositoryImpl(DatabaseModule.favoriteDao)
    }
}
