package com.example.letssopt.domain.exception

sealed class UserException: Exception() {
    class MyIdNotFound: UserException() // 저장된 내 아이디가 없음
}
