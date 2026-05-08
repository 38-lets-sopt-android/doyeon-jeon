package com.example.letssopt.data.repository

import com.example.letssopt.core.network.model.ApiError
import com.example.letssopt.core.network.util.ApiResponseHandler
import com.example.letssopt.data.local.datasource.AuthLocalDataSource
import com.example.letssopt.data.remote.dto.request.SignInRequestDto
import com.example.letssopt.data.remote.dto.request.SignUpRequestDto
import com.example.letssopt.data.remote.service.AuthService
import com.example.letssopt.domain.exception.AuthException
import com.example.letssopt.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val apiResponseHandler: ApiResponseHandler,
    private val authLocalDataSource: AuthLocalDataSource,
    private val authService: AuthService,
) : AuthRepository {
    override suspend fun register(
        loginId: String,
        email: String,
        password: String,
        name: String,
        age: Int,
        part: String,
    ): Result<Unit> = apiResponseHandler.safeUnitApiCall {
        authService.postSignUp(
            body = SignUpRequestDto(
                loginId = loginId,
                password = password,
                name = name,
                email = email,
                age = age,
                part = part,
            )
        )
    }.recoverCatching {
        throw if (it is ApiError) {
            when (it.serverCode) {
                "AUTH_409_001" -> AuthException.Register.IdDuplicated()
                "AUTH_400_002" -> AuthException.Register.IdInvalid()
                "AUTH_400_004" -> AuthException.Register.PasswordInvalid()
                "AUTH_400_006" -> AuthException.Register.NameInvalid()
                "AUTH_400_008" -> AuthException.Register.EmailInvalid()
                "AUTH_400_009", "AUTH_400_010" -> AuthException.Register.AgeInvalid()
                "AUTH_400_012" -> AuthException.Register.PartInvalid()
                else -> it
            }
        } else it
    }

    override suspend fun login(
        loginId: String,
        password: String,
    ): Result<Unit> = apiResponseHandler.safeApiCall {
        authService.postSignIn(
            body = SignInRequestDto(
                loginId = loginId,
                password = password
            )
        )
    }
        .mapCatching { saveUserId(it.userId).getOrThrow() }
        .recoverCatching {
            throw if (it is ApiError && it.serverCode == "AUTH_401_001") AuthException.Login.IdOrPasswordMismatch()
            else it
        }

    override fun getIsLoggedIn(): Result<Boolean> = runCatching {
        authLocalDataSource.getUserId() != null
    }

    private fun saveUserId(userId: Long) = runCatching {
        authLocalDataSource.setUserId(userId)
    }
}
