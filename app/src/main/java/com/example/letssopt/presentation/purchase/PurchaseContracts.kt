package com.example.letssopt.presentation.purchase

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState
import com.example.letssopt.domain.model.ContentModel

@Immutable
data class PurchaseUiState(
    val purchaseContents: List<ContentModel> = emptyList(),
): UiState

sealed interface PurchaseUiEffect: UiEffect {
    data class ShowToast(@param:StringRes val message: Int): PurchaseUiEffect
}
