package com.example.letssopt.presentation.profile.my

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.letssopt.core.base.Async
import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState
import com.example.letssopt.presentation.profile.model.MyInfoUiModel

@Immutable
data class MyProfileUiState(
    val myInfos: Async<List<MyInfoUiModel>> = Async.Init,
): UiState

sealed interface MyProfileUiEffect: UiEffect {
    data class ShowToast(@param:StringRes val message: Int): MyProfileUiEffect
    data object NavigateToProfileList: MyProfileUiEffect
    data object NavigateToLogin: MyProfileUiEffect
}
