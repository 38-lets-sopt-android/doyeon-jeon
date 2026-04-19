package com.example.letssopt.presentation.auth.register

import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState

data object RegisterUiState: UiState

sealed interface RegisterUiEffect: UiEffect {
    data class BackToLogin(val email: String, val password: String): RegisterUiEffect
    data class ShowToast(val message: String): RegisterUiEffect
}
