package com.example.letssopt.presentation.archive

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.util.HandleUiEffects
import com.example.letssopt.domain.model.ContentModel
import com.example.letssopt.presentation.archive.component.FavoriteGrid

@Composable
fun ArchiveRoute(
    viewModel: ArchiveViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HandleUiEffects(viewModel.uiEffect) { effect ->
        when (effect) {
            is ArchiveUiEffect.ShowToast -> Toast.makeText(
                context, effect.message, Toast.LENGTH_SHORT
            ).show()
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
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        Spacer(Modifier.height(70.dp))

        Text(
            text = "찜한 목록",
            color = LETSSOPTTheme.colors.white,
            style = LETSSOPTTheme.typography.h3,
        )

        Spacer(Modifier.height(45.dp))

        FavoriteGrid(
            contents = favoriteContents,
            onDeleteClick = onDeleteClick,
        )
    }
}
