package com.example.letssopt.domain.repository

interface AuthRepository {
    suspend fun register(loginId: String, email: String, password: String, name: String, age: Int, part: String): Result<Unit>

    suspend fun login(loginId: String, password: String): Result<Unit>

    fun getIsLoggedIn(): Result<Boolean>
}
