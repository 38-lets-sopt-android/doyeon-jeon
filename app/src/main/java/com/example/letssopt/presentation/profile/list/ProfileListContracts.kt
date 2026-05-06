package com.example.letssopt.presentation.profile.list

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.letssopt.core.base.Async
import com.example.letssopt.core.base.UiEffect
import com.example.letssopt.core.base.UiState
import com.example.letssopt.domain.model.UserModel

@Immutable
data class ProfileListUiState(
    val userInfos: Async<List<UserModel>> = Async.Init,
) : UiState

sealed interface ProfileListUiEffect : UiEffect {
    data class ShowToast(@param:StringRes val message: Int) : ProfileListUiEffect
}
