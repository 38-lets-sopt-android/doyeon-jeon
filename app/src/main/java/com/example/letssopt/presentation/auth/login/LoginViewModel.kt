package com.example.letssopt.presentation.auth.login

import android.app.Activity.RESULT_OK
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.presentation.auth.util.EMAIL_KEY
import com.example.letssopt.presentation.auth.util.PASSWORD_KEY

private enum class LoginValidationError(val message: String) {
    EMAIL_NOT_FOUND("존재하지 않는 이메일입니다"),
    PASSWORD_MISMATCH("비밀번호가 올바르지 않습니다"),
}

class LoginViewModel : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()) {
    val emailState = TextFieldState()
    val passwordState = TextFieldState()

    val loginEnabled by derivedStateOf { emailState.text.isNotBlank() && passwordState.text.isNotBlank() }

    fun onLoginClick() {
        val error = validateLoginInputs()

        if (error != null) {
            return sendEffect(LoginUiEffect.ShowToast(error.message))
        }

        sendEffect(LoginUiEffect.ShowToast("로그인에 성공했습니다"))
        sendEffect(LoginUiEffect.NavigateToMain)
    }

    fun onRegisterClick() {
        emailState.clearText()
        passwordState.clearText()
        sendEffect(LoginUiEffect.NavigateToRegister)
    }

    fun handleRegisterResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val email = result.data?.getStringExtra(EMAIL_KEY) ?: ""
            val password = result.data?.getStringExtra(PASSWORD_KEY) ?: ""
            updateState { copy(resultEmail = email, resultPassword = password) }
        }
    }

    private fun validateLoginInputs(): LoginValidationError? {
        val emailText = emailState.text.toString()
        val passwordText = passwordState.text.toString()

        return when {
            emailText != currentState.resultEmail -> LoginValidationError.EMAIL_NOT_FOUND
            passwordText != currentState.resultPassword -> LoginValidationError.PASSWORD_MISMATCH
            else -> null
        }
    }
}
