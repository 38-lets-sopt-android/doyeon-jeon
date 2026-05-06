package com.example.letssopt.presentation.archive

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.presentation.common.component.ContentGridSection
import com.example.letssopt.core.common.extension.toast
import com.example.letssopt.core.common.util.HandleUiEffects
import com.example.letssopt.domain.model.ContentModel

@Composable
fun ArchiveRoute(
    modifier: Modifier = Modifier,
    viewModel: ArchiveViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HandleUiEffects(viewModel.uiEffect) { effect ->
        when (effect) {
            is ArchiveUiEffect.ShowToast -> context.toast(effect.message)
        }
    }

    ArchiveScreen(
        favoriteContents = uiState.favoriteContents,
        onDeleteClick = viewModel::onDelete,
        modifier = modifier
    )
}

@Composable
private fun ArchiveScreen(
    favoriteContents: List<ContentModel>,
    onDeleteClick: (ContentModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    ContentGridSection(
        title = stringResource(R.string.archive_title),
        contents = favoriteContents,
        onDeleteClick = onDeleteClick,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 70.dp),
    )
}
