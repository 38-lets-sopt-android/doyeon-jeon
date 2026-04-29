package com.example.letssopt.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.common.extension.verticalThumbnail
import com.example.letssopt.core.designsystem.component.BaseAsyncImage
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.domain.model.ContentModel

@Composable
fun ContentGridSection(
    title: String,
    contents: List<ContentModel>,
    onDeleteClick: ((ContentModel) -> Unit)? = null,
    onSaveClick: ((ContentModel) -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        stickyHeader {
            Text(
                text = title,
                modifier = Modifier
                    .background(LETSSOPTTheme.colors.background)
                    .padding(bottom = 21.dp),
                color = LETSSOPTTheme.colors.white,
                style = LETSSOPTTheme.typography.h3,
            )
        }

        items(items = contents, key = { it.id }) { content ->
            GridItem(
                title = content.title,
                thumbnailUrl = content.thumbnailUrl,
                onDeleteClick = onDeleteClick?.let { { it(content) } },
                onSaveClick = onSaveClick?.let { { it(content) } },
                modifier = Modifier.animateItem()
            )
        }
    }
}

@Composable
private fun GridItem(
    title: String?,
    thumbnailUrl: String,
    onDeleteClick: (() -> Unit)? = null,
    onSaveClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Box {
            BaseAsyncImage(
                imageUrl = thumbnailUrl,
                modifier = Modifier.verticalThumbnail(maxWidth = true),
            )

            onSaveClick?.let {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_purchase_ticket_18),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(all = 6.dp)
                        .size(28.dp)
                        .clip(CircleShape)
                        .clickable { it() }
                        .background(LETSSOPTTheme.colors.black)
                        .wrapContentSize(Alignment.Center),
                    tint = Color.Unspecified,
                )
            }
        }

        title?.let {
            Text(
                text = it,
                modifier = Modifier.padding(top = 6.dp),
                color = LETSSOPTTheme.colors.white,
                style = LETSSOPTTheme.typography.body,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }

        onDeleteClick?.let {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_archive_delete_24),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .clickable { it() }
                    .padding(all = 12.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
private fun ContentGridSectionPreview() {
    LETSSOPTTheme {
        ContentGridSection(
            title = "찜한 목록",
            contents = ContentModel.purchaseContents,
            onDeleteClick = {},
            onSaveClick = {},
        )
    }
}
