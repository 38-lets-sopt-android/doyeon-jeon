package com.example.letssopt.presentation.profile.my

import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.base.Async
import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.domain.exception.UserException
import com.example.letssopt.domain.repository.UserRepository
import com.example.letssopt.presentation.profile.model.MyInfoUiModel
import kotlinx.coroutines.launch

class MyProfileViewModel(
    private val userRepository: UserRepository,
) : BaseViewModel<MyProfileUiState, MyProfileUiEffect>(MyProfileUiState()) {
    init {
        getMyInfo()
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            updateState { copy(myInfos = Async.Loading) }

            userRepository.getMyInfo()
                .onSuccess { response ->
                    val data = listOf(
                        MyInfoUiModel(
                            title = R.string.label_id,
                            content = response.loginId,
                        ),
                        MyInfoUiModel(
                            title = R.string.label_name,
                            content = response.name,
                        ),
                        MyInfoUiModel(
                            title = R.string.label_email,
                            content = response.email,
                        ),
                        MyInfoUiModel(
                            title = R.string.label_age,
                            content = response.age.toString(),
                        ),
                        MyInfoUiModel(
                            title = R.string.label_part,
                            content = response.part,
                        ),
                    )
                    updateState { copy(myInfos = Async.Success(data)) }
                }
                .onFailure { error ->
                    if (error is UserException.MyIdNotFound) {
                        sendEffect(MyProfileUiEffect.ShowToast(R.string.profile_msg_fail_needlogin))
                        sendEffect(MyProfileUiEffect.NavigateToLogin)
                    } else {
                        sendEffect(MyProfileUiEffect.ShowToast(R.string.profile_msg_fail))
                    }
                }
        }
    }

    fun onNavigateToProfileList() {
        sendEffect(MyProfileUiEffect.NavigateToProfileList)
    }
}
