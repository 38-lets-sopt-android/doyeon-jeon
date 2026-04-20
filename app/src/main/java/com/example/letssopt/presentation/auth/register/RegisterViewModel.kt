package com.example.letssopt.presentation.auth.register

import android.util.Patterns
import androidx.annotation.StringRes
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.example.letssopt.R
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.data.local.AuthRepository

private enum class RegisterValidationError(@param:StringRes val message: Int) {
    EMAIL_INVALID(R.string.register_msg_fail_emailinvalid),
    PASSWORD_INVALID_LENGTH(R.string.register_msg_fail_passwordlength),
    PASSWORD_MISMATCH(R.string.register_msg_fail_passwordmismatch)
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

        handleRegister(emailText, passwordText)
    }

    private fun handleRegister(
        emailText: String,
        passwordText: String,
    ) {
        AuthRepository.register(emailText, passwordText)
            .onSuccess {
                sendEffect(RegisterUiEffect.ShowToast(R.string.register_msg_success))
                sendEffect(RegisterUiEffect.BackToLogin)
            }
            .onFailure {
                sendEffect(RegisterUiEffect.ShowToast(R.string.register_msg_fail))
            }
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
