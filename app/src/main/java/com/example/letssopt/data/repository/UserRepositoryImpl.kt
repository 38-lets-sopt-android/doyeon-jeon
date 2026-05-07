package com.example.letssopt.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.letssopt.core.network.util.ApiResponseHandler
import com.example.letssopt.data.local.datasource.AuthLocalDataSource
import com.example.letssopt.data.mapper.toModel
import com.example.letssopt.data.remote.datasource.UserPagingSource
import com.example.letssopt.data.remote.service.UserService
import com.example.letssopt.domain.exception.UserException
import com.example.letssopt.domain.model.MyInfoModel
import com.example.letssopt.domain.model.UserModel
import com.example.letssopt.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

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

    override fun getUsers(): Flow<PagingData<UserModel>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { UserPagingSource(userService) }
        ).flow

    private fun getMyId(): Result<Long?> = runCatching { authLocalDataSource.getUserId() }
}
