package com.example.letssopt.presentation.purchase

import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.data.di.RepositoryModule
import com.example.letssopt.domain.model.ContentModel
import kotlinx.coroutines.launch

class PurchaseViewModel : BaseViewModel<PurchaseUiState, PurchaseUiEffect>(PurchaseUiState()) {
    private val favoriteRepository = RepositoryModule.favoriteRepository

    init {
        getPurchaseContents()
    }

    private fun getPurchaseContents() {
        updateState { copy(purchaseContents = ContentModel.purchaseContents) }
    }

    fun onSave(content: ContentModel) {
        viewModelScope.launch {
            favoriteRepository.save(content)
                .onSuccess {
                    sendEffect(PurchaseUiEffect.ShowToast(R.string.purchase_success_save))
                }
        }
    }
}
