package com.example.letssopt.presentation.archive

import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.domain.model.ContentModel

class ArchiveViewModel: BaseViewModel<ArchiveUiState, ArchiveUiEffect>(ArchiveUiState()) {
    init {
        getFavoriteContents()
    }

    private fun getFavoriteContents() {
        updateState { copy(favoriteContents = ContentModel.favoriteContents) }
    }

    fun onDelete(content: ContentModel) {
        val deletedContents = currentState.favoriteContents.filterNot {
            it.id == content.id
        }
        updateState { copy(favoriteContents = deletedContents) }
        sendEffect(ArchiveUiEffect.ShowToast("찜한 목록에서 삭제 되었어요"))
    }
}
