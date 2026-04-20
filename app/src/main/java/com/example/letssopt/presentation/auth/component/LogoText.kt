package com.example.letssopt.presentation.auth.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme

@Composable
fun LogoText(
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.watcha),
        modifier = modifier,
        color = LETSSOPTTheme.colors.primaryRed,
        style = LETSSOPTTheme.typography.logo,
    )
}
