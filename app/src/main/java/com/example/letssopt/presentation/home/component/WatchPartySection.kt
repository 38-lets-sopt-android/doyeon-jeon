package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.domain.model.WatchPartyModel

@Composable
fun WatchPartySection(
    parties: List<WatchPartyModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "왓챠 파티",
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
            items(items = parties, key = { it.id }) { party ->
                PartyItem(
                    thumbnail = party.thumbnailUrl,
                    startInfo = party.startInfo,
                    hashtag = party.hashtag,
                )
            }
        }
    }
}

@Composable
private fun PartyItem(
    thumbnail: String,
    startInfo: String,
    hashtag: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.width(196.dp),
    ) {
        Box {
            AsyncImage(
                model = thumbnail,
                contentDescription = null,
                modifier = Modifier.size(196.dp, 139.dp),
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(LETSSOPTTheme.colors.surface),
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_main_notification_18),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-5).dp, y = 7.dp)
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(LETSSOPTTheme.colors.white)
                    .wrapContentSize(Alignment.Center),
                tint = Color.Unspecified,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(LETSSOPTTheme.colors.surface)
                .padding(horizontal = 8.dp)
                .padding(top = 6.dp, bottom = 7.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = startInfo,
                color = LETSSOPTTheme.colors.primaryRed,
                style = LETSSOPTTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = hashtag,
                color = LETSSOPTTheme.colors.white,
                style = LETSSOPTTheme.typography.sh3,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
private fun WatchPartySectionPreview() {
    LETSSOPTTheme {
        WatchPartySection(
            parties = WatchPartyModel.parties,
        )
    }
}
