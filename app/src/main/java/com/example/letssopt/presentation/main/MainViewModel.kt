package com.example.letssopt.presentation.main

import androidx.lifecycle.ViewModel
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.data.di.RepositoryModule
import com.example.letssopt.presentation.auth.navigation.AuthGraph
import com.example.letssopt.presentation.home.navigation.HomeRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val authRepository = RepositoryModule.authRepository
    private val _startDestination = MutableStateFlow<Route?>(null)
    val startDestination = _startDestination.asStateFlow()

    init {
        val dest = when (authRepository.getIsLoggedIn().getOrNull()) {
            true -> HomeRoute.Home
            else -> AuthGraph
        }
        _startDestination.update { dest }
    }
}
