package com.example.letssopt.data.auth

import com.example.letssopt.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authPrefs: AuthPreferences,
) : AuthRepository {
    override fun register(email: String, password: String): Result<Unit> {
        return runCatching {
            authPrefs.setEmail(email)
            authPrefs.setPassword(password)
        }
    }

    override fun login(email: String, password: String): Result<Unit> {
        return runCatching {
            val savedEmail = authPrefs.getEmail()
            val savedPassword = authPrefs.getPassword()

            when {
                savedEmail == null || savedPassword == null -> throw AuthException.NoAccountFound()
                savedEmail != email -> throw AuthException.EmailNotFound()
                savedPassword != password -> throw AuthException.PasswordMismatch()
                else -> saveLoggedInState()
            }
        }
    }

    private fun saveLoggedInState() = runCatching {
        authPrefs.setLoggedInState(true)
    }

    override fun getIsLoggedIn(): Result<Boolean> = runCatching {
        authPrefs.getIsLoggedIn()
    }
}
