package com.example.letssopt.presentation.auth.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.letssopt.R
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.data.auth.AuthException
import com.example.letssopt.data.di.RepositoryModule

class LoginViewModel : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState) {
    private val authRepository = RepositoryModule.authRepository

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
        authRepository.login(emailText, passwordText)
            .onSuccess {
                sendEffect(LoginUiEffect.ShowToast(R.string.login_msg_success))
                sendEffect(LoginUiEffect.NavigateToMain)
            }
            .onFailure { error ->
                val message = if (error is AuthException) {
                    when (error) {
                        is AuthException.EmailNotFound -> R.string.login_msg_fail_emailnotfound
                        is AuthException.NoAccountFound -> R.string.login_msg_fail_needregister
                        is AuthException.PasswordMismatch -> R.string.login_msg_fail_passwordmismatch
                    }
                } else R.string.login_msg_fail
                sendEffect(LoginUiEffect.ShowToast(message))
            }
    }

    fun onRegisterClick() {
        emailState.clearText()
        passwordState.clearText()
        sendEffect(LoginUiEffect.NavigateToRegister)
    }
}
