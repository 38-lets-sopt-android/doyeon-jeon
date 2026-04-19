package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.component.BaseAsyncImage
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.extension.verticalThumbnail
import com.example.letssopt.domain.model.ContentModel

@Composable
fun UpcomingContentsSection(
    contents: List<ContentModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "공개 예정 콘텐츠",
                color = LETSSOPTTheme.colors.white,
                style = LETSSOPTTheme.typography.h3,
            )

            Text(
                text = "더보기",
                color = LETSSOPTTheme.colors.textSecondary,
                style = LETSSOPTTheme.typography.cap1,
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(items = contents, key = { it.id }) { content ->
                BaseAsyncImage(
                    imageUrl = content.thumbnailUrl,
                    modifier = Modifier.verticalThumbnail(),
                )
            }
        }
    }
}

@Preview
@Composable
private fun UpcomingContentsSectionPreview() {
    LETSSOPTTheme {
        UpcomingContentsSection(
            contents = ContentModel.upcomingContents,
        )
    }
}
