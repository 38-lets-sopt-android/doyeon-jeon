package com.example.letssopt.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.domain.model.UserModel

@Composable
fun UserInfoList(
    pagingData: LazyPagingItems<UserModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            count = pagingData.itemCount,
            key = pagingData.itemKey { it.userId }
        ) { index ->
            pagingData[index]?.let { info ->
                InfoItem(
                    userId = info.userId.toString(),
                    name = info.name,
                    part = info.part,
                )
            }
        }
    }
}

@Composable
private fun InfoItem(
    userId: String,
    name: String,
    part: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(LETSSOPTTheme.colors.black)
            .border(width = 1.dp, color = LETSSOPTTheme.colors.white)
            .padding(vertical = 10.dp)
            .padding(start = 68.dp, end = 34.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = userId,
                color = LETSSOPTTheme.colors.white,
                style = LETSSOPTTheme.typography.button,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = name,
                color = LETSSOPTTheme.colors.textSecondary,
                style = LETSSOPTTheme.typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Text(
            text = part,
            color = LETSSOPTTheme.colors.white,
            style = LETSSOPTTheme.typography.caption,
        )
    }
}
