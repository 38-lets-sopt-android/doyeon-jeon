package com.example.letssopt.presentation.profile.my

import com.example.letssopt.core.base.BaseViewModel
import com.example.letssopt.domain.repository.UserRepository

class MyProfileViewModel(
    private val userRepository: UserRepository,
) : BaseViewModel<MyProfileUiState, MyProfileUiEffect>(MyProfileUiState()) {
    fun onNavigateToProfileList() {}
}
