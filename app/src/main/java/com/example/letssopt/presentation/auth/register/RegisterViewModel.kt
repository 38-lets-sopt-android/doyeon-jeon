package com.example.letssopt.presentation.auth.register

import androidx.annotation.StringRes
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.domain.exception.AuthException
import com.example.letssopt.domain.repository.AuthRepository
import kotlinx.coroutines.launch

private enum class RegisterValidationError(@param:StringRes val message: Int) {
    PASSWORD_MISMATCH(R.string.register_msg_fail_passwordmismatch),
    AGE_NOT_INTEGER(R.string.register_msg_fail_agenotinteger);
}


class RegisterViewModel(
    private val authRepository: AuthRepository,
) : BaseViewModel<RegisterUiState, RegisterUiEffect>(RegisterUiState) {
    val idState = TextFieldState()
    val passwordState = TextFieldState()
    val passwordConfirmState = TextFieldState()
    val nameState = TextFieldState()
    val emailState = TextFieldState()
    val ageState = TextFieldState()
    val partState = TextFieldState("안드로이드")

    val registerEnabled by derivedStateOf {
        idState.text.isNotBlank() && emailState.text.isNotBlank()
                && passwordState.text.isNotBlank() && passwordConfirmState.text.isNotBlank()
                && nameState.text.isNotBlank() && ageState.text.isNotBlank()
                && partState.text.isNotBlank()
    }

    fun onRegisterClick() {
        val idText = idState.text.toString()
        val passwordText = passwordState.text.toString()
        val passwordConfirmText = passwordConfirmState.text.toString()
        val nameText = nameState.text.toString()
        val emailText = emailState.text.toString()
        val ageText = ageState.text.toString()
        val partText = partState.text.toString()

        val error = validateRegisterInputs(
            passwordText = passwordText,
            passwordConfirmText = passwordConfirmText,
            ageText = ageText,
        )
        if (error != null) {
            return sendEffect(RegisterUiEffect.ShowToast(error.message))
        }

        handleRegister(
            idText = idText,
            passwordText = passwordText,
            nameText = nameText,
            emailText = emailText,
            ageText = ageText,
            partText = partText,
        )
    }

    private fun handleRegister(
        idText: String,
        passwordText: String,
        nameText: String,
        emailText: String,
        ageText: String,
        partText: String,
    ) {
        val ageInt = runCatching { ageText.toInt() }.getOrNull() ?: return

        viewModelScope.launch {
            authRepository.register(
                loginId = idText,
                email = emailText,
                password = passwordText,
                name = nameText,
                age = ageInt,
                part = partText,
            )
                .onSuccess {
                    sendEffect(RegisterUiEffect.ShowToast(R.string.register_msg_success))
                    sendEffect(RegisterUiEffect.BackToLogin)
                }
                .onFailure {
                    val message = if (it is AuthException) {
                        when (it) {
                            is AuthException.IdDuplicated -> R.string.register_msg_fail_idduplicated
                            is AuthException.AgeInvalid -> R.string.register_msg_fail_ageinvalid
                            is AuthException.EmailInvalid -> R.string.register_msg_fail_emailinvalid
                            is AuthException.IdInvalid -> R.string.register_msg_fail_idinvalid
                            is AuthException.NameInvalid -> R.string.register_msg_fail_nameinvalid
                            is AuthException.PartInvalid -> R.string.register_msg_fail_partinvalid
                            is AuthException.PasswordInvalid -> R.string.register_msg_fail_passwordlength
                            else -> R.string.register_msg_fail
                        }
                    } else R.string.register_msg_fail
                    sendEffect(RegisterUiEffect.ShowToast(message))
                }
        }
    }

    private fun validateRegisterInputs(
        passwordText: String,
        passwordConfirmText: String,
        ageText: String,
    ): RegisterValidationError? {
        return when {
            passwordText != passwordConfirmText
                -> RegisterValidationError.PASSWORD_MISMATCH

            runCatching { ageText.toInt() }.getOrNull() == null
                -> RegisterValidationError.AGE_NOT_INTEGER

            else -> null
        }
    }
}
