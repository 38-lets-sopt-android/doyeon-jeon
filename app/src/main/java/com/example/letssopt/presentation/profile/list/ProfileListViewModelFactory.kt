package com.example.letssopt.presentation.profile.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letssopt.data.di.RepositoryModule

class ProfileListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileListViewModel(
                userRepository = RepositoryModule.userRepository,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
