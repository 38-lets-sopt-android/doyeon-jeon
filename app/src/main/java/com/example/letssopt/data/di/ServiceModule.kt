package com.example.letssopt.data.di

import com.example.letssopt.data.di.NetworkModule.instance
import com.example.letssopt.data.remote.service.AuthService
import com.example.letssopt.data.remote.service.UserService

object ServiceModule {
    val authService: AuthService by lazy { instance.create(AuthService::class.java) }

    val userService: UserService by lazy { instance.create(UserService::class.java) }
}
