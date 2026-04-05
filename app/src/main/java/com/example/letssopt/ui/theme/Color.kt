package com.example.letssopt.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val PrimaryRed = Color(0xFFE8003C)
val Background = Color(0xFF000000)
val Surface = Color(0xFF2A2A2A)
val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFF999999)
val Placeholder = Color(0xFF666666)

@Immutable
class LETSSOPTColors(
    val primaryRed: Color,
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val placeHolder: Color,
)

val defaultLETSSOPTColors = LETSSOPTColors(
    primaryRed = PrimaryRed,
    background = Background,
    surface = Surface,
    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    placeHolder = Placeholder
)

val LocalLETSSOPTColorsProvider = staticCompositionLocalOf { defaultLETSSOPTColors }
