package com.example.letssopt.presentation.archive

import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.data.di.RepositoryModule
import com.example.letssopt.domain.model.ContentModel
import kotlinx.coroutines.launch

class ArchiveViewModel : BaseViewModel<ArchiveUiState, ArchiveUiEffect>(ArchiveUiState()) {
    private val favoriteRepository = RepositoryModule.favoriteRepository

    init {
        getFavoriteContents()
    }

    private fun getFavoriteContents() {
        viewModelScope.launch {
            favoriteRepository.getAll().collect { result ->
                updateState { copy(favoriteContents = result.getOrNull() ?: emptyList()) }
            }
        }
    }

    fun onDelete(content: ContentModel) {
        viewModelScope.launch {
            favoriteRepository.delete(content)
                .onSuccess {
                    sendEffect(ArchiveUiEffect.ShowToast(R.string.archive_msg_success_delete))
                }
        }
    }
}
