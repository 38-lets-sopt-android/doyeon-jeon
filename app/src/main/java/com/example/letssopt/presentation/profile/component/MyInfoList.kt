package com.example.letssopt.presentation.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.profile.model.MyInfoUiModel

@Composable
fun MyInfoList(
    myInfos: List<MyInfoUiModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        myInfos.forEach { model ->
            InfoItem(
                title = stringResource(model.title),
                content = model.content,
            )
        }
    }
}

@Composable
private fun InfoItem(
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Text(
            text = title,
            color = LETSSOPTTheme.colors.white,
            style = LETSSOPTTheme.typography.button,
        )

        Text(
            text = content,
            color = LETSSOPTTheme.colors.textSecondary,
            style = LETSSOPTTheme.typography.caption,
        )
    }
}
