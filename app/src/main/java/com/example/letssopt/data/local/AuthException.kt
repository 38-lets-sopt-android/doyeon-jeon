package com.example.letssopt.data.local

sealed class AuthException : Exception() {
    class NoAccountFound : AuthException()
    class EmailNotFound : AuthException()
    class PasswordMismatch : AuthException()
}
