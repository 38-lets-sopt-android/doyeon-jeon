package com.example.letssopt.data.remote.service

import com.example.letssopt.core.network.model.BaseResponse
import com.example.letssopt.data.remote.dto.request.SignInRequestDto
import com.example.letssopt.data.remote.dto.request.SignUpRequestDto
import com.example.letssopt.data.remote.dto.response.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    suspend fun postSignUp(@Body body: SignUpRequestDto): BaseResponse<Unit>

    @POST("/api/v1/auth/signin")
    suspend fun postSignIn(@Body body: SignInRequestDto): BaseResponse<SignInResponseDto>
}
