package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.base.BaseAsyncImage
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.home.model.ContentModel

@Composable
fun NewContentsSection(
    contents: List<ContentModel>,
    modifier: Modifier = Modifier,
) {
    val state = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = state)

    Column(
        modifier = modifier,
    ) {
        Text(
            text = "방금 막 도착한 신상 컨텐츠",
            modifier = Modifier.padding(start = 19.dp),
            color = LETSSOPTTheme.colors.white,
            style = LETSSOPTTheme.typography.h3,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "예능부터 드라마까지!",
            modifier = Modifier.padding(start = 19.dp),
            color = LETSSOPTTheme.colors.textSecondary,
            style = LETSSOPTTheme.typography.sh1,
        )

        Spacer(Modifier.height(24.dp))

        LazyRow(
            state = state,
            flingBehavior = flingBehavior,
            contentPadding = PaddingValues(horizontal = 19.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(items = contents, key = { it.id }) { content ->
                BaseAsyncImage(
                    imageUrl = content.thumbnailUrl,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .size(280.dp, 160.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun NewContentsSectionPreview() {
    LETSSOPTTheme {
        NewContentsSection(
            contents = ContentModel.newContents,
        )
    }
}
