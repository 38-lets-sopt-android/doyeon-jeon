package com.example.letssopt.domain.repository

interface AuthRepository {
    fun register(email: String, password: String): Result<Unit>

    fun login(email: String, password: String): Result<Unit>

    fun getIsLoggedIn(): Result<Boolean>
}
