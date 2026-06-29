package com.example.letssopt.data.mapper

import com.example.letssopt.data.remote.dto.response.UserListResponseDto
import com.example.letssopt.data.remote.dto.response.UserResponseDto
import com.example.letssopt.domain.model.MyInfoModel
import com.example.letssopt.domain.model.UserModel

fun UserResponseDto.toModel(): MyInfoModel =
    MyInfoModel(
        loginId = this.loginId,
        name = this.name,
        email = this.email,
        age = this.age,
        part = this.part,
    )

fun UserListResponseDto.toModel(): List<UserModel> =
    this.users.map { user ->
        UserModel(
            userId = user.id,
            name = user.name,
            part = user.part,
        )
    }
