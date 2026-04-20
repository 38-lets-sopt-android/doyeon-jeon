package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.component.BaseAsyncImage
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.common.extension.verticalThumbnail
import com.example.letssopt.domain.model.ContentModel

@Composable
fun WhatgorithmSection(
    contents: List<ContentModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_main_whatgorithm),
            contentDescription = null,
            modifier = Modifier.padding(start = 16.dp),
            tint = Color.Unspecified,
        )

        Spacer(Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.home_title_whatgorithm),
                color = LETSSOPTTheme.colors.textSecondary,
                style = LETSSOPTTheme.typography.h3,
            )

            Text(
                text = stringResource(R.string.action_more),
                color = LETSSOPTTheme.colors.textSecondary,
                style = LETSSOPTTheme.typography.cap1,
            )
        }

        Spacer(Modifier.height(6.dp))

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
private fun WhatgorithmSectionPreview() {
    LETSSOPTTheme {
        WhatgorithmSection(
            contents = ContentModel.whatgorithmContents
        )
    }
}
