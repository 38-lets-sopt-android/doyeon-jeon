package com.example.letssopt.presentation.home

import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.domain.model.ContentModel
import com.example.letssopt.domain.model.WatchPartyModel

class HomeViewModel: BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()) {
    init {
        getNewContents()
        getWhatgorithmContents()
        getUpcomingContents()
        getPartyContents()
    }

    private fun getNewContents() {
        updateState { copy(newContents = ContentModel.newContents) }
    }

    private fun getWhatgorithmContents() {
        updateState { copy(whatgorithmContents = ContentModel.whatgorithmContents) }
    }

    private fun getUpcomingContents() {
        updateState { copy(upcomingContents = ContentModel.upcomingContents) }
    }

    private fun getPartyContents() {
        updateState { copy(partyContents = WatchPartyModel.parties) }
    }

    fun onProfileClick() {
        sendEffect(HomeUiEffect.NaviageToMyProfile)
    }
}
