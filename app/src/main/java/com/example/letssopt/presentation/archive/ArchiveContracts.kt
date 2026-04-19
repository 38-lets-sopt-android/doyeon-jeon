package com.example.letssopt.presentation.archive

import androidx.compose.runtime.Immutable
import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState
import com.example.letssopt.domain.model.ContentModel

@Immutable
data class ArchiveUiState(
    val favoriteContents: List<ContentModel> = emptyList(),
): UiState

sealed interface ArchiveUiEffect: UiEffect {
    data class ShowToast(val message: String): ArchiveUiEffect
}
