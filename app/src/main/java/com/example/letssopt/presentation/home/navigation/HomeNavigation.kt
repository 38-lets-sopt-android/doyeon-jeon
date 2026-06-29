package com.example.letssopt.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.home.HomeRoute
import com.example.letssopt.presentation.profile.navigation.navigateToMyProfile
import kotlinx.serialization.Serializable

sealed interface HomeRoute : Route {
    @Serializable
    data object Home : HomeRoute
}

fun NavController.navigateToHome() {
    navigate(HomeRoute.Home) {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
    paddingValues: PaddingValues,
) {
    composable<HomeRoute.Home> {
        HomeRoute(
            navigateToMyProfile = navController::navigateToMyProfile,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
