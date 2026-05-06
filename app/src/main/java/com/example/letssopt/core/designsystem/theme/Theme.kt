package com.example.letssopt.core.designsystem.theme

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryRed,
    onPrimary = White,
    background = Background,
    onBackground = White,
    surface = Surface,
    onSurface = White,
)

object LETSSOPTTheme {
    val colors: LETSSOPTColors
        @Composable
        @ReadOnlyComposable
        get() = LocalLETSSOPTColorsProvider.current

    val typography: LETSSOPTTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalLETSSOPTTypographyProvider.current
}

@Composable
fun LETSSOPTTheme(
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    val activity = LocalActivity.current

    SideEffect {
        activity?.window?.let {
            it.run {
                WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = false
            }
        }
    }

    CompositionLocalProvider(
        LocalLETSSOPTColorsProvider provides defaultLETSSOPTColors,
        LocalLETSSOPTTypographyProvider provides defaultLETSSOPTTypography,
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            content = content
        )
    }
}