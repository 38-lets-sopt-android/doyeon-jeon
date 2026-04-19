package com.example.letssopt.presentation.main.bottombar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun MainBottomBar(
    currentTab: MainTab,
    onClick: (MainTab)->Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp)
            .background(LETSSOPTTheme.colors.background)
            .padding(horizontal = 18.dp, vertical = 11.dp),
        horizontalArrangement = Arrangement.spacedBy(21.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MainTab.entries.forEach { tab ->
            BottomBarItem(
                label = tab.label,
                iconRes = tab.iconRes,
                selected = tab == currentTab,
                onClick = { onClick(tab) },
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    label: String,
    @DrawableRes iconRes: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val color = if (selected) LETSSOPTTheme.colors.white else LETSSOPTTheme.colors.disabled

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(role = Role.Tab) { onClick() }
            .padding(top = 4.dp, bottom = 1.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = label,
            tint = color,
        )

        Text(
            text = label,
            color = color,
            style = LETSSOPTTheme.typography.body2,
        )
    }
}

private class MainBottomBarPreviewParameter: PreviewParameterProvider<MainTab>{
    override val values: Sequence<MainTab>
        get() = MainTab.entries.asSequence()
}

@Preview
@Composable
private fun MainBottomBarPreview(
    @PreviewParameter(MainBottomBarPreviewParameter::class) currentTab: MainTab,
) {
    LETSSOPTTheme {
        MainBottomBar(
            currentTab = currentTab,
            onClick = {},
        )
    }
}
