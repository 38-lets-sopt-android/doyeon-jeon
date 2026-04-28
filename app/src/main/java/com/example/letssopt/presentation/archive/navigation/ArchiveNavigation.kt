package com.example.letssopt.presentation.archive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.archive.ArchiveRoute
import kotlinx.serialization.Serializable

sealed interface ArchiveRoute : Route {
    @Serializable
    data object Archive : ArchiveRoute
}

fun NavGraphBuilder.archiveNavGraph(
    paddingValues: PaddingValues,
) {
    composable<ArchiveRoute.Archive> {
        ArchiveRoute(
            modifier = Modifier.padding(paddingValues)
        )
    }
}
