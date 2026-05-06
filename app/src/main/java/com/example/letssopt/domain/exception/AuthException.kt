package com.example.letssopt.domain.exception

sealed class AuthException : Exception() {
    class IdOrPasswordMismatch : AuthException() // 아이디 또는 비밀번호 불일치
    class IdDuplicated: AuthException() // 사용 중인 아이디
    class IdInvalid : AuthException() // 아이디가 4~20자 사이가 아님
    class PasswordInvalid : AuthException() // 비밀번호가 8~20자 사이가 아님
    class NameInvalid : AuthException() // 이름이 10자 이하가 아님
    class EmailInvalid : AuthException() // 이메일 형식이 올바르지 않음
    class AgeInvalid : AuthException() // 나이가 1~150 범위가 아님
    class PartInvalid : AuthException() // 파트가 안드로이드/웹/iOS 중 하나가 아님
}
