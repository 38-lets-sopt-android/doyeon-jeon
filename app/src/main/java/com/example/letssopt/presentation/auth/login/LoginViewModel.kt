package com.example.letssopt.presentation.auth.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.data.local.AuthException
import com.example.letssopt.data.local.AuthRepository

class LoginViewModel : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState) {
    val emailState = TextFieldState()
    val passwordState = TextFieldState()

    val loginEnabled by derivedStateOf { emailState.text.isNotBlank() && passwordState.text.isNotBlank() }

    fun onLoginClick() {
        val emailText = emailState.text.toString()
        val passwordText = passwordState.text.toString()

        handleLogin(emailText, passwordText)
    }

    private fun handleLogin(
        emailText: String,
        passwordText: String,
    ) {
        AuthRepository.login(emailText, passwordText)
            .onSuccess {
                sendEffect(LoginUiEffect.ShowToast("로그인에 성공했습니다"))
                sendEffect(LoginUiEffect.NavigateToMain)
            }
            .onFailure { error ->
                val message = if (error is AuthException) {
                    when (error) {
                        is AuthException.EmailNotFound -> "존재하지 않는 이메일입니다"
                        is AuthException.NoAccountFound -> "회원가입을 먼저 수행해주세요"
                        is AuthException.PasswordMismatch -> "비밀번호가 올바르지 않습니다"
                    }
                } else "로그인에 실패했습니다"
                sendEffect(LoginUiEffect.ShowToast(message))
            }
    }

    fun onRegisterClick() {
        emailState.clearText()
        passwordState.clearText()
        sendEffect(LoginUiEffect.NavigateToRegister)
    }
}
