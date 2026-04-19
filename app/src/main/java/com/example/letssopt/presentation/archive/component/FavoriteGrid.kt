package com.example.letssopt.presentation.archive.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.base.BaseAsyncImage
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.extension.verticalThumbnail
import com.example.letssopt.domain.model.ContentModel

@Composable
fun FavoriteGrid(
    contents: List<ContentModel>,
    onDeleteClick: (ContentModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items(items = contents, key = { it.id }) { content ->
            FavoriteItem(
                thumbnailUrl = content.thumbnailUrl,
                onDeleteClick = { onDeleteClick(content) },
            )
        }
    }
}

@Composable
private fun FavoriteItem(
    thumbnailUrl: String,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BaseAsyncImage(
            imageUrl = thumbnailUrl,
            modifier = Modifier.verticalThumbnail(),
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_archive_delete_24),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onDeleteClick() }
                .padding(all = 12.dp),
            tint = Color.Unspecified
        )
    }
}

@Preview
@Composable
private fun FavoriteGridPreview() {
    LETSSOPTTheme {
        FavoriteGrid(
            contents = ContentModel.favoriteContents,
            onDeleteClick = {},
        )
    }
}
