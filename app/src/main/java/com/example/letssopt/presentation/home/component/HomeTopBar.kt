package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun HomeTopBar(modifier: Modifier = Modifier) {
    val topBarIcons = remember {
        listOf(
            R.drawable.ic_main_watch_24,
            R.drawable.ic_main_noti_24,
            R.drawable.ic_main_profile_24
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(LETSSOPTTheme.colors.background)
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 14.dp,
            alignment = Alignment.End,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        topBarIcons.forEach { icon ->
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }
    }
}

@Preview
@Composable
private fun HomeTopBarPreview() {
    LETSSOPTTheme {
        HomeTopBar()
    }
}
