package com.example.letssopt.presentation.auth.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.domain.exception.AuthException
import com.example.letssopt.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
) : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState) {
    val idState = TextFieldState()
    val passwordState = TextFieldState()

    val loginEnabled by derivedStateOf { idState.text.isNotBlank() && passwordState.text.isNotBlank() }

    fun onLoginClick() {
        val idText = idState.text.toString()
        val passwordText = passwordState.text.toString()

        handleLogin(idText, passwordText)
    }

    private fun handleLogin(
        idText: String,
        passwordText: String,
    ) {
        viewModelScope.launch {
            authRepository.login(idText, passwordText)
                .onSuccess {
                    sendEffect(LoginUiEffect.ShowToast(R.string.login_msg_success))
                    sendEffect(LoginUiEffect.NavigateToMain)
                }
                .onFailure { error ->
                    val message = if (error is AuthException.NameInvalid) {
                        R.string.login_msg_fail_inputmismatch
                    } else R.string.login_msg_fail
                    sendEffect(LoginUiEffect.ShowToast(message))
                }
        }
    }

    fun onRegisterClick() {
        idState.clearText()
        passwordState.clearText()
        sendEffect(LoginUiEffect.NavigateToRegister)
    }
}