package com.example.letssopt.presentation.profile.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.auth.navigation.navigateToLogin
import com.example.letssopt.presentation.profile.list.ProfileListRoute
import com.example.letssopt.presentation.profile.my.MyProfileRoute
import kotlinx.serialization.Serializable

sealed interface ProfileRoute : Route {
    @Serializable
    data object MyProfile : ProfileRoute

    @Serializable
    data object ProfileList : ProfileRoute
}

fun NavController.navigateToMyProfile() {
    navigate(ProfileRoute.MyProfile)
}

fun NavController.navigateToProfileList() {
    navigate(ProfileRoute.ProfileList)
}

fun NavGraphBuilder.profileNavGraph(
    navController: NavController,
    paddingValues: PaddingValues,
) {
    composable<ProfileRoute.MyProfile>(
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } },
    ) {
        MyProfileRoute(
            navigateToProfileList = navController::navigateToProfileList,
            navigateToLogin = navController::navigateToLogin,
            modifier = Modifier.padding(paddingValues),
        )
    }

    composable<ProfileRoute.ProfileList>(
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { -it } },
        popEnterTransition = { slideInHorizontally { -it } },
        popExitTransition = { slideOutHorizontally { it } },
    ) {
        ProfileListRoute(
            modifier = Modifier.padding(paddingValues),
        )
    }
}
