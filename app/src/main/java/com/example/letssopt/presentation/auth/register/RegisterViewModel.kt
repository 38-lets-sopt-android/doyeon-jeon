package com.example.letssopt.presentation.auth.register

import android.util.Patterns
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.letssopt.core.base.BaseViewModel

private enum class RegisterValidationError(val message: String) {
    EMAIL_INVALID("올바른 이메일 형식을 입력해주세요"),
    PASSWORD_INVALID_LENGTH("비밀번호는 8~12자로 입력해주세요"),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다")
}


class RegisterViewModel : BaseViewModel<RegisterUiState, RegisterUiEffect>(RegisterUiState) {
    val emailState = TextFieldState()
    val passwordState = TextFieldState()
    val passwordConfirmState = TextFieldState()

    val registerEnabled by derivedStateOf {
        emailState.text.isNotBlank() && passwordState.text.isNotBlank() && passwordConfirmState.text.isNotBlank()
    }

    fun onRegisterClick() {
        val emailText = emailState.text.toString()
        val passwordText = passwordState.text.toString()
        val passwordConfirmText = passwordConfirmState.text.toString()

        val error = validateRegisterInputs(emailText, passwordText, passwordConfirmText)
        if (error != null) {
            return sendEffect(RegisterUiEffect.ShowToast(error.message))
        }

        sendEffect(RegisterUiEffect.ShowToast("회원가입에 성공했습니다"))
        sendEffect(RegisterUiEffect.BackToLogin(emailText, passwordText))
    }

    private fun validateRegisterInputs(
        emailText: String,
        passwordText: String,
        passwordConfirmText: String,
    ): RegisterValidationError? {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(emailText)
                .matches() -> RegisterValidationError.EMAIL_INVALID

            passwordText.length !in 8..12 -> RegisterValidationError.PASSWORD_INVALID_LENGTH
            passwordText != passwordConfirmText -> RegisterValidationError.PASSWORD_MISMATCH
            else -> null
        }
    }
}
