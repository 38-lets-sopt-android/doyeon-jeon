package com.example.letssopt.presentation.profile.list

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.domain.model.UserModel
import com.example.letssopt.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class ProfileListViewModel(
    userRepository: UserRepository,
) : BaseViewModel<ProfileListUiState, ProfileListUiEffect>(ProfileListUiState) {
    val pagingData: Flow<PagingData<UserModel>> = userRepository
        .getUsers()
        .cachedIn(viewModelScope)
}
