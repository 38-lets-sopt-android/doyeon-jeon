package com.example.letssopt.presentation.profile.list

import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.base.Async
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.domain.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileListViewModel(
    private val userRepository: UserRepository,
) : BaseViewModel<ProfileListUiState, ProfileListUiEffect>(ProfileListUiState()) {
    init {
        getUsersInfo()
    }

    private fun getUsersInfo() {
        viewModelScope.launch {
            updateState { copy(userInfos = Async.Loading) }
            userRepository.getUsers()
                .onSuccess {
                    updateState {
                        copy(userInfos = if (it.isEmpty()) Async.Empty else Async.Success(it))
                    }
                }
                .onFailure {
                    sendEffect(ProfileListUiEffect.ShowToast(R.string.profilelist_msg_fail))
                }
        }
    }
}
