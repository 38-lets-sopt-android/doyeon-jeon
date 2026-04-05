package com.example.letssopt.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun LogoText(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "watcha",
        modifier = modifier,
        color = LETSSOPTTheme.colors.primaryRed,
        style = LETSSOPTTheme.typography.logo,
    )
}