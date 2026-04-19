package com.example.letssopt.presentation.auth.login

import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState

object LoginUiState: UiState

sealed interface LoginUiEffect: UiEffect {
    data object NavigateToRegister: LoginUiEffect
    data object NavigateToMain: LoginUiEffect
    data class ShowToast(val message: String): LoginUiEffect
}
