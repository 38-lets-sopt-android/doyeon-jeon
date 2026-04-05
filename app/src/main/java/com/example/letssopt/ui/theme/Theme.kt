package com.example.letssopt.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryRed,
    onPrimary = TextPrimary,
    background = Background,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
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
    val activity = LocalContext.current as? Activity

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