package com.example.letssopt.data.repository

import com.example.letssopt.core.network.util.ApiResponseHandler
import com.example.letssopt.data.local.datasource.AuthLocalDataSource
import com.example.letssopt.data.mapper.toModel
import com.example.letssopt.data.remote.service.UserService
import com.example.letssopt.domain.exception.UserException
import com.example.letssopt.domain.model.MyInfoModel
import com.example.letssopt.domain.model.UserModel
import com.example.letssopt.domain.repository.UserRepository

class UserRepositoryImpl(
    private val apiResponseHandler: ApiResponseHandler,
    private val authLocalDataSource: AuthLocalDataSource,
    private val userService: UserService,
) : UserRepository {
    override suspend fun getMyInfo(): Result<MyInfoModel> {
        val myId = getMyId().getOrNull() ?: return Result.failure(UserException.MyIdNotFound())

        return apiResponseHandler.safeApiCall {
            userService.getUser(myId)
        }.map { it.toModel() }
    }

    override suspend fun getUsers(): Result<List<UserModel>> =
        apiResponseHandler.safeApiCall {
            userService.getUserList()
        }.map { it.toModel() }

    private fun getMyId(): Result<Long?> = runCatching { authLocalDataSource.getUserId() }
}
