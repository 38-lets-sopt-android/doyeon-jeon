package com.example.letssopt.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.letssopt.presentation.home.component.HomeTopBar
import com.example.letssopt.presentation.home.component.NewContentsSection
import com.example.letssopt.presentation.home.component.UpcomingContentsSection
import com.example.letssopt.presentation.home.component.WatchPartySection
import com.example.letssopt.presentation.home.component.WhatgorithmSection
import com.example.letssopt.presentation.home.model.ContentModel
import com.example.letssopt.presentation.home.model.WatchPartyModel

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        newContents = uiState.newContents,
        whatgorithmContents = uiState.whatgorithmContents,
        upcomingContents = uiState.upcomingContents,
        partyContents = uiState.partyContents,
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    newContents: List<ContentModel>,
    whatgorithmContents: List<ContentModel>,
    upcomingContents: List<ContentModel>,
    partyContents: List<WatchPartyModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        HomeTopBar()

        Spacer(Modifier.height(24.dp))

        NewContentsSection(
            contents = newContents,
        )

        Spacer(Modifier.height(24.dp))

        WhatgorithmSection(
            contents = whatgorithmContents,
        )

        Spacer(Modifier.height(24.dp))

        UpcomingContentsSection(
            contents = upcomingContents,
        )

        Spacer(Modifier.height(24.dp))

        WatchPartySection(
            parties = partyContents,
        )

        Spacer(Modifier.height(24.dp))
    }
}
