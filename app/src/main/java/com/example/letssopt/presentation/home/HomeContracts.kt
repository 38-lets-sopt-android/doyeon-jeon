package com.example.letssopt.presentation.home

import androidx.compose.runtime.Immutable
import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState
import com.example.letssopt.presentation.home.model.ContentModel
import com.example.letssopt.presentation.home.model.WatchPartyModel

@Immutable
data class HomeUiState(
    val newContents: List<ContentModel> = emptyList(),
    val whatgorithmContents: List<ContentModel> = emptyList(),
    val upcomingContents: List<ContentModel> = emptyList(),
    val partyContents: List<WatchPartyModel> = emptyList(),
): UiState

data object HomeUiEffect: UiEffect
