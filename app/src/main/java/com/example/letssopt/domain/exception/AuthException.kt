package com.example.letssopt.domain.exception

sealed class AuthException : Exception() {
    sealed class Register : AuthException() {
        class IdDuplicated : Register() // 사용 중인 아이디
        class IdInvalid : Register() // 아이디가 4~20자 사이가 아님
        class PasswordInvalid : Register() // 비밀번호가 8~20자 사이가 아님
        class NameInvalid : Register() // 이름이 10자 이하가 아님
        class EmailInvalid : Register() // 이메일 형식이 올바르지 않음
        class AgeInvalid : Register() // 나이가 1~150 범위가 아님
        class PartInvalid : Register() // 파트가 안드로이드/웹/iOS 중 하나가 아님
    }

    sealed class Login : AuthException() {
        class IdOrPasswordMismatch : Login() // 아이디 또는 비밀번호 불일치
    }
}
