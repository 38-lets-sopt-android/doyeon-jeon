package com.example.letssopt.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserListResponseDto(
    @SerialName("users")
    val users: List<User>,
) {
    @Serializable
    data class User(
        @SerialName("id")
        val id: Long,
        @SerialName("name")
        val name: String,
        @SerialName("part")
        val part: String,
    )
}
