package com.example.letssopt.presentation.archive

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState
import com.example.letssopt.domain.model.ContentModel

@Immutable
data class ArchiveUiState(
    val favoriteContents: List<ContentModel> = emptyList(),
): UiState

sealed interface ArchiveUiEffect: UiEffect {
    data class ShowToast(@param:StringRes val message: Int): ArchiveUiEffect
}
