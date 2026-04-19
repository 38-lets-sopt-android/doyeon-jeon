package com.example.letssopt.presentation.auth.login

import androidx.compose.runtime.Immutable
import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState

@Immutable
data class LoginUiState(
    val resultEmail: String = "",
    val resultPassword: String = "",
): UiState

sealed interface LoginUiEffect: UiEffect {
    data object NavigateToRegister: LoginUiEffect
    data object NavigateToMain: LoginUiEffect
    data class ShowToast(val message: String): LoginUiEffect
}
