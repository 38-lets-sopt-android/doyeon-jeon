package com.example.letssopt.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.domain.model.UserModel

@Composable
fun UserInfoList(
    usersInfo: List<UserModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(usersInfo, key = { it.userId }) { info ->
            InfoItem(
                userId = info.userId.toString(),
                name = info.name,
                part = info.part,
            )
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
    val colors = LETSSOPTTheme.colors

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.black)
            .drawBehind {
                drawRect(
                    color = colors.white,
                    style = Stroke(1.dp.toPx())
                )
            }
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
                color = colors.white,
                style = LETSSOPTTheme.typography.button,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = name,
                color = colors.textSecondary,
                style = LETSSOPTTheme.typography.caption,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Text(
            text = part,
            color = colors.white,
            style = LETSSOPTTheme.typography.caption,
        )
    }
}

@Preview
@Composable
private fun UserInfoListPreview() {
    LETSSOPTTheme {
        UserInfoList(
            usersInfo = listOf(
                UserModel(0L, "이름", "안드로이드"),
                UserModel(1L, "이름", "iOS")
            ),
        )
    }
}
