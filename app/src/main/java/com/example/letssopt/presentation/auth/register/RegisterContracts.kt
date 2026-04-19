package com.example.letssopt.presentation.auth.register

import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState

data object RegisterUiState: UiState

sealed interface RegisterUiEffect: UiEffect {
    data object BackToLogin: RegisterUiEffect
    data class ShowToast(val message: String): RegisterUiEffect
}
