package com.example.letssopt.data.remote.service

import com.example.letssopt.core.network.model.BaseResponse
import com.example.letssopt.data.remote.dto.response.UserListResponseDto
import com.example.letssopt.data.remote.dto.response.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/api/v1/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Long): BaseResponse<UserResponseDto>

    @GET("/api/v1/users")
    suspend fun getUserList(): BaseResponse<UserListResponseDto>
}
