package com.example.letssopt.domain.repository

import androidx.paging.PagingData
import com.example.letssopt.domain.model.MyInfoModel
import com.example.letssopt.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getMyInfo(): Result<MyInfoModel>

    fun getUsers(): Flow<PagingData<UserModel>>
}
