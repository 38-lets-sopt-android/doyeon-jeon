package com.example.letssopt.domain.repository

import com.example.letssopt.domain.model.MyInfoModel
import com.example.letssopt.domain.model.UserModel

interface UserRepository {
    suspend fun getMyInfo(): Result<MyInfoModel>

    suspend fun getUsers(): Result<List<UserModel>>
}
