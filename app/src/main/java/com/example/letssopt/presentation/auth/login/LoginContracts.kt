package com.example.letssopt.presentation.auth.login

import androidx.annotation.StringRes
import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState

object LoginUiState: UiState

sealed interface LoginUiEffect: UiEffect {
    data object NavigateToRegister: LoginUiEffect
    data object NavigateToMain: LoginUiEffect
    data class ShowToast(@param:StringRes val message: Int): LoginUiEffect
}
